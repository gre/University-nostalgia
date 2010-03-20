package controllers;

import play.i18n.Messages;
import play.mvc.*;
import play.mvc.Http.Header;
import models.*;
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
        Header header = request.headers.get("user-agent");
        boolean mobileEnabled = false;
        mobileEnabled = (header != null && header.value().contains("Smartphone"));
        String style = session.get("style");
        if(style!=null && style.equals("mobile"))
          mobileEnabled = true;
        renderArgs.put("isMobile", mobileEnabled);
    }
    
    // ~~~~~~~~~~~~ Actions
    
    public static void setMobile(boolean active) {
      session.put("style", active?"mobile":"default");
      redirect("/");
    }
    
    public static void index() {
        if(connectedUser() != null)
            Main.index();
        render();
    }
    
    public static void signup() {
        if(connectedUser() != null)
            Main.index();
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
            informError();
            signup();
        }
        if(User.findByEmail(email)!=null) {
            validation.keep();
            params.flash();
            informError();
            signup();
        }
        new User(email, password, firstname, lastname).save();
        informSuccess();
        login();
    }

    public static void login() {
        if(connectedUser() != null)
            Main.index();
        render();
    }

    public static void authenticate(String email, String password) {
        User user = User.findByEmail(email);
        if (user == null || !user.checkPassword(password)) {
            flash.error(Messages.get("validation.loginfailed", "Bad email or bad password"));
            flash.put("email", email);
            informError();
            login();
        } else if (!user.isActivate()) {
            flash.error(Messages.get("validation.notconfirmed", "This account is not confirmed"));
            flash.put("email", email);
            informError();
            login();
        }
        connect(user);
        Main.index();
    }

    public static void logout() {
        flash.success("You've been logged out");
        String style = session.get("style");
        session.clear();
        session.put("style", style);
        informSuccess();
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
    
    static void informSuccess() {
        flash("info", Messages.get("info.success"));
        flash("infotype", "success");
        flash.keep();
    }
    static void informError() {
        flash("info", Messages.get("info.error"));
        flash("infotype", "error");
        flash.keep();
    }

}
