package models;

import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Diploma extends Model {  

  @Required
  public String name;
  
  public Diploma(String name) {
    this.name = name;
  }
  
  public String toString() {
    return name;
  }
}
