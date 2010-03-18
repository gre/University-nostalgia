package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Speciality extends Model {  
  public String name;
  
  public Speciality(String name) {
    this.name = name;
  }
}
