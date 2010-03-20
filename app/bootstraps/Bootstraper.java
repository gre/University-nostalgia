package bootstraps;

import java.util.List;

import play.jobs.*;
import models.*;
import play.test.Fixtures;
import play.Logger;
import java.util.ArrayList;

@OnApplicationStart
public class Bootstraper extends Job {

	public void doJob() {
    Logger.info("Bootstraping...");
    Fixtures.deleteAll();
    Fixtures.load("bootstraps/data.yml");
    Logger.info("...success.");
    
    
    // make users all friends
    List<User> users = new ArrayList<User>();
    users.add(User.findByEmail("user"));
    users.add(User.findByEmail("master"));
    users.add(User.findByEmail("gaetan@iut-fbleau.fr"));
    users.add(User.findByEmail("nicolae@iut-fbleau.fr"));
    for (User u : users)
      for (User user : users)
        if (u.id!=user.id)
          User.makeFriends(u, user);
    
    users = User.findAll();
	}

}
