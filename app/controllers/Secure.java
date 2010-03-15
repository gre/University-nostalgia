package controllers;
import play.mvc.*;

public class Secure extends Application {
    
    @Before
    static void checkSecure() {
        if(connectedUser() == null) 
            index();
    }
    
}
