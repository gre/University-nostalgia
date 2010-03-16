package controllers;
import models.*;
import java.util.List;

import play.data.validation.Required;

public class Finder extends Secure {
  
	public static void index() {
        render();
	}
	
  public static void search(@Required String type, @Required String search) {
	    if (validation.hasErrors()) {
	        validation.keep();
	        params.flash();
	        index();
	    }
		List<User> results = null;
		if (type.equals("person"))
			results = User.findBySearch(search);
		else if (type.equals("university"))
			results = null;
		render(search, type, results);
  }
}
