package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Diploma extends Model {  

  public String name;
  
  public Diploma(String name) {
    this.name = name;
  }
}
