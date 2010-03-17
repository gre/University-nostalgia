package bootstraps;

import java.util.List;

import play.jobs.*;
import models.*;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstraper extends Job {

	public void doJob() {
		if(User.count()==0) {
			Fixtures.deleteAll();
			/* Fixtures.load("jobs/bootstraps/diploma.yml"); */
			Fixtures.load("bootstraps/user.yml");
			/*
			// make users all friends
			List<User> users = User.findAll();
			for (User u : users)
				for (User user : users)
					if (!u.equals(user))
						user.friends.add(u);
			for (User u : users)
				u.save();
			*/
		}
	}

}
