package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class UniversityYear extends Model {  
  @OneToOne
  University university;
  
  Long year;
  
  @OneToOne
  Speciality speciality;
  
  @OneToOne
  Diploma diploma; // Null if no diploma this year, else we have a promotion this year
}
