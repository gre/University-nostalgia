package models;

import javax.persistence.*;
import play.db.jpa.*;

import java.util.*;

@Entity
public class University extends Model {  
    String name;
    
    public static List<University> findBySearch(String name) {
        return find("name like ?1", "%"+name+"%").fetch();
    }
}
