package controllers;
import models.*;
import java.util.List;
import play.data.validation.*;

public class AdminDiplomas extends SecureAdmin {
  public static void index() {
    List<Diploma> diplomas = Diploma.findAll();
    render(diplomas);
  }
  public static void create(@Required String name) {
    if (validation.hasErrors()) {
      validation.keep();
      params.flash();
      informError();
      index();
    }
    new Diploma(name).save();
    informSuccess();
    index();
  }
  public static void delete(@Required Long id) {
    notFoundIfNull(id);
    Diploma d = Diploma.findById(id);
    notFoundIfNull(d);
    d.delete();
    informSuccess();
    index();
  }
}
