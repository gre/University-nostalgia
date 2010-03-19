package models;

import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Speciality extends Model {  
  
  @Required
  public String name;
  
  public Speciality(String name) {
    this.name = name;
  }
  
  
  public String toString() {
    return name;
  }
}
