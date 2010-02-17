package models;

import javax.persistence.*; 
import play.db.jpa.*;
import play.data.validation.*;

import java.util.List;

@Entity
public class User extends Model {
  
  @ManyToMany
  List<User> friends;
  
  @OneToMany
  List<Message> messages;
  
  @Required
  String firstname;
  
  @Required
  String lastname;
  
  @Email
  String email;
  
  @OneToMany
  List<UniversityYear> universities;
  
  @OneToMany
  List<Corporation> corporations;
  
  String address;
  String phone;
}
