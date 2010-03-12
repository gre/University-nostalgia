package controllers;

import play.mvc.*;
import models.User;
import play.data.validation.*;
import play.Logger;

public class Application extends Controller {
    
    // ~~~~~~~~~~~~ @Before interceptors
    @Before
    static void logRequest() {
        Logger.debug("%-7s %-20s (%s) -> %s", request.method, request.path, request.contentType, request.action);
    }
    
    @Before
    static void globals() {
        renderArgs.put("connected", connectedUser());
    }

    @Before
    static void checkSecure() {
        Secure secure = getActionAnnotation(Secure.class);
        if (secure != null) {
            if (connectedUser() == null)
            	login();
            else if(secure.admin() && !connectedUser().isAdmin())
            	forbidden();
        }
    }
    // ~~~~~~~~~~~~ Actions
    
    public static void signup() {
        render();
    }

    public static void register(
    		@Required @Email String email, 
    		@Required @MinSize(5) String password, 
    		@Equals("password") String password2, 
    		@Required String firstname,
    		@Required String lastname) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            signup();
        }
        User user = new User(email, password, firstname, lastname);
        flash.error("Oops ... (the email cannot be sent)");
        login();
    }

    public static void login() {
        render();
    }

    public static void authenticate(String email, String password) {
        User user = User.findByEmail(email);
        Logger.info(user.checkPassword(password) ? "true" : "false");
        if (user == null || !user.checkPassword(password)) {
            flash.error("Bad email or bad password");
            flash.put("email", email);
            login();
        } else if (!user.isActivate()) {
            flash.error("This account is not confirmed");
            flash.put("email", email);
            login();
        }
        connect(user);
        flash.success("Welcome back %s !", user.lastname);
        Main.index();
    }

    public static void logout() {
        flash.success("You've been logged out");
        session.clear();
        Main.index();
    }
    
    // ~~~~~~~~~~~~ Some utils
    
    static void connect(User user) {
        session.put("logged", user.id);
    }

    static User connectedUser() {
        String userId = session.get("logged");
        return userId == null ? null : (User) User.findById(Long.parseLong(userId));
    }

}
