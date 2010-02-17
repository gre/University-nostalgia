package models;

import javax.persistence.*; 
import play.db.jpa.*;
import play.data.validation.*;

import java.util.List;

@Entity
public class User extends Model {
  
  @Email
  @Required
  public String email;
  
  @Required
  public String password;
  
  @ManyToMany
  List<User> friends;
  
  @OneToMany
  List<Message> messages;
  
  @Required
  String firstname;
  
  @Required
  String lastname;
  
  @OneToMany
  List<UniversityYear> universities;
  
  @OneToMany
  List<Corporation> corporations;
  
  String address;
  String phone;
}
