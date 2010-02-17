package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class MessageSent extends Message {
  
  @ManyToOne
  User receiver;

}