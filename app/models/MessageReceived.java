package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class MessageReceived extends Message {
  
  @ManyToOne
  User sender;

}