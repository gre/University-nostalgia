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
    Fixtures.load("bootstraps/diploma.yml");
    Fixtures.load("bootstraps/user.yml");
    Fixtures.load("bootstraps/university.yml");
    Fixtures.load("bootstraps/speciality.yml");
    Logger.info("...success.");
    
    
    // make users all friends
    List<User> users = User.findAll();
    for (User u : users)
      for (User user : users)
        if (u.id!=user.id)
          User.makeFriends(u, user);
	}

}
