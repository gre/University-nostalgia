package controllers;
import models.*;
import java.util.List;

import play.data.validation.Required;
import play.Logger;

public class Finder extends Secure {
  
  public static void index() {
        searchPerson(null);
  }
  
  public static void searchPerson(@Required String search) {
      if (validation.hasErrors())
          render();
    List<User> results = User.findBySearch(search);
    render(search, results);
  }
  
  public static void searchUniversity(@Required String search) {
      if (validation.hasErrors())
          render();
    List<University> results = University.findBySearch(search);
    render(search, results);
  }
}
