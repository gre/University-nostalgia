package controllers;
import models.*;
import java.util.List;
import play.data.validation.*;

public class ProfileEmploi extends Secure {
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