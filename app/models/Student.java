package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Student extends Model {
  String firstname;
  String lastname;
  
  @OneToOne
  Diploma diploma;
  
  Long graduationYear;
  
}
