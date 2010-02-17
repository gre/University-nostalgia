package models;

import javax.persistence.*; 
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class FriendRequest extends Model {
  User caller;
  User concerned;
}
