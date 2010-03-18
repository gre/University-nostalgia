package models;

import javax.persistence.*;
import play.db.jpa.*;

import java.util.*;

@Entity
public class University extends Model {  
    public String name;
    
    public University(String name) {
      this.name = name;
    }
    
    public static List<University> findBySearch(String name) {
        return find("name like ?1", "%"+name+"%").fetch();
    }
}
