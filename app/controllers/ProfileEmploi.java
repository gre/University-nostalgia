package controllers;
import models.*;
import java.util.List;
import play.data.validation.*;
import play.mvc.*;

public class ProfileEmploi extends Secure {
  
    @Before
    static void profileGlobals() {
      Profile.profileGlobals();
    }
  
    public static void index() {
        List<Corporation> corporations = Corporation.findForUser(connectedUser());
        render(corporations);
    }
    
    public static void create(@Required String name,@Required Long year) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            informError();
        }
        new Corporation(name,year,connectedUser()).save();
        informSuccess();
        index();
    }
    
    public static void delete(@Required Long id) {
        Corporation item = Corporation.findById(id);
        notFoundIfNull(item);
        noRightsIfNotMe(item.user);
        item.delete();
        informSuccess();
        index();
    }
}