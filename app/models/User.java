package models;

import javax.persistence.*; 
import play.db.jpa.*;
import play.data.validation.*;

import java.util.List;

@Entity
public class User extends Model {
  
  @OneToMany
  List<Friend> friends;
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
  List<MessageReceived> inbox;
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
  List<MessageSent> sendbox;
  
  @Required
  String firstname;
  
  @Required
  String lastname;
  
  @Email
  String email;
}
