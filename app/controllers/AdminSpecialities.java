package controllers;
import models.*;
import java.util.List;
import play.data.validation.*;

public class AdminSpecialities extends SecureAdmin {
  public static void index() {
    List<Speciality> specialities = Speciality.findAll();
    render(specialities);
  }
  public static void create(@Required String name) {
    if (validation.hasErrors()) {
      validation.keep();
      params.flash();
      informError();
    }
    new Speciality(name).save();
    informSuccess();
    index();
  }
  public static void delete(@Required Long id) {
    notFoundIfNull(id);
    Speciality item = Speciality.findById(id);
    notFoundIfNull(item);
    item.delete();
    informSuccess();
    index();
  }
}
