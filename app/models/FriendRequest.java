package models;

import javax.persistence.*; 
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class FriendRequest extends Model {
  @OneToOne
  User caller;
  @OneToOne
  User concerned;
}
