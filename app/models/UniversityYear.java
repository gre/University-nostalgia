package models;

import java.util.List;

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
  
  @ManyToOne
  User user;
  
  public static List<UniversityYear> find
  (University university, Long year, Speciality speciality) {
	  return find(
			  (university==null? "true" : "university.id="+university.id)+" and "+
			  (year==null? "true" : "year="+year)+" and "+
			  (speciality==null? "true" : "speciality.id="+speciality.id)
			 ).fetch();
  }
}
