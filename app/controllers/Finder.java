package controllers;
import models.*;
import java.util.List;
import java.util.ArrayList;

import play.data.validation.Required;
import play.Logger;

public class Finder extends Secure {
  
  public static void index() {
        searchPerson(null);
  }
  
  public static void searchPerson(@Required String search) {
    User current = connectedUser();
      if (validation.hasErrors())
          render();
    List<UserInfo> results = new ArrayList<UserInfo>();
    for(User u : User.findBySearch(search))
			if(u.id != current.id)
      	results.add(new UserInfo(u, current));
    render(search, results);
  }
  
  public static void searchUniversity(@Required String search) {
      if (validation.hasErrors())
          render();
    List<University> results = University.findBySearch(search);
    render(search, results);
  }
}
