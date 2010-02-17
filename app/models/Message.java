package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Message extends Model {
  
  @Lob
  String content;
  
  @ManyToOne
  User user; // receiver or sender

  Box box; // inbox or sendbox

  public enum Box { 
    input, 
    sendbox
  };
}