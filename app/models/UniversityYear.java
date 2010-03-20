package models;

import java.util.List;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class UniversityYear extends Model {  
  @OneToOne
  public University university;
  
  public Long year;
  
  @OneToOne
  public Speciality speciality;
  
  @OneToOne
  public Diploma diploma; // Null if no diploma this year, else we have a promotion this year
  
  @ManyToOne
  public User user;
  
  public static List<UniversityYear> find
  (University university, Long year, Speciality speciality) {
	  return find(
			  (university==null? "true" : "university.id="+university.id)+" and "+
			  (year==null? "true" : "year="+year)+" and "+
			  (speciality==null? "true" : "speciality.id="+speciality.id)
			 ).fetch();
  }
  
  
  public static List<UniversityYear> findForUser(User user) {
    return find("user = ?1 order by year desc", user).fetch();
  }
}
