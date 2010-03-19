package controllers;
import play.mvc.*;

public class SecureAdmin extends Application {
  
    @Before
    static void checkSecureAdmin() {
        if (connectedUser() == null || connectedUser().isAdmin() == false)
            redirect("/");
    }
    
}
