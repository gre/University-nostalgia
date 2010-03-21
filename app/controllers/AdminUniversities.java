package controllers;
import models.*;
import java.util.List;
import play.data.validation.*;

public class AdminUniversities extends SecureAdmin {
  public static void index() {
    List<University> universities = University.findAll();
    render(universities);
  }
  public static void create(@Required String name) {
    if (validation.hasErrors()) {
      validation.keep();
      params.flash();
      informError();
      index();
    }
    new University(name).save();
    informSuccess();
    index();
  }
  public static void delete(@Required Long id) {
    notFoundIfNull(id);
    University item = University.findById(id);
    notFoundIfNull(item);
    item.delete();
    informSuccess();
    index();
  }
}
