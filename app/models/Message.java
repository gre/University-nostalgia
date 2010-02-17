package models;

import javax.persistence.*;
import play.db.jpa.*;

@MappedSuperclass
public class Message extends Model {
  
  @Lob
  String content;

}