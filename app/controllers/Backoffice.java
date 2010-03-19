package controllers;
import models.*;
import play.data.validation.*;
import java.util.*;
import play.mvc.*;

public class Backoffice extends Secure {
    
    @Before
    static void checkSecureAdmin() {
        if (connectedUser() == null || connectedUser().isAdmin() == false)
            redirect("/");
    }
    public static void index() {
        List inactive = User.getInactiveUsers();
        System.out.println(inactive.size());
        render(inactive);
    }
    public static void activateUser(Long id) {
        User user = User.findById(id);
        notFoundIfNull(user);
        user.activate();
        user.save();
        index();
    }
}