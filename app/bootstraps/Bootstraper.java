package bootstraps;

import java.util.List;

import play.jobs.*;
import models.*;
import play.test.Fixtures;
 
@OnApplicationStart
public class Bootstraper extends Job {
    
    public void doJob() {
      Fixtures.deleteAll();
      /*Fixtures.load("jobs/bootstraps/diploma.yml");*/
      Fixtures.load("bootstraps/user.yml");
      List<User> users = User.findAll();
      for(User u : users)
          for(User user : users)
              if(!u.equals(user)) 
                  user.friends.add(u); // make users all friends 
      for(User u : users)
          u.save();
    }
    
}
