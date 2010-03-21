package models;

import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;

import java.util.*;

@Entity
public class Corporation extends Model {  
  @Required
  public String name;
  
  @Required
  public Long year;
  
  @ManyToOne
  public User user;
  
    public Corporation(String name, Long year, User user) {
      this.name = name;
      this.year = year;
      this.user = user;
    }
    
    public static List<Corporation> findBySearch(String name) {
        return find("name like ?1", "%"+name+"%").fetch();
    }
    
    public String toString() {
      return name;
    }
    
    public static List<Corporation> findForUser(User user) {
      return find("user = ?1 order by year desc", user).fetch();
    }
  
}
