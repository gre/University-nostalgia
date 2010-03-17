package models;

import javax.persistence.*; 
import play.db.jpa.*;
import play.data.validation.*;
import java.util.List;

@Entity
public class FriendRequest extends Model {
  @OneToOne
  public User caller;
  @OneToOne
  public User concerned;
  
  public FriendRequest(User caller, User concerned) {
    this.caller = caller;
    this.concerned = concerned;
  }
  
  public static FriendRequest findBy2Users(User u1, User u2) {
    return find("caller.id=?1 and concerned.id=?2 or "+
                "caller.id=?2 and concerned.id=?1", u1.id, u2.id).first();
  }
  
  public static List<FriendRequest> findRequestFor(User user) {
    return find("concerned.id=?1", user.id).fetch();
  }
}
