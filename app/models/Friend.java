package models;

import javax.persistence.*; 
import play.db.jpa.*;

@Entity
public class Friend extends Model {
  
  User user;
  
}
