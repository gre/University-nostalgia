package bootstraps;

import java.util.List;

import play.jobs.*;
import models.*;
import play.test.Fixtures;
import java.util.ArrayList;

@OnApplicationStart
public class Bootstraper extends Job {

	public void doJob() {
		if(User.count()==0) { // only bootstrap if no users
			Fixtures.deleteAll();
			Fixtures.load("bootstraps/diploma.yml");
			Fixtures.load("bootstraps/user.yml");
			Fixtures.load("bootstraps/university.yml");
			Fixtures.load("bootstraps/speciality.yml");
			
			/*
			// make users all friends
			List<User> users = User.findAll();
			for (User u : users) {
				u.friends = new ArrayList<User>();
				u.save();
				for (User user : users)
					if (u.id!=user.id)
						u.friends.add(user);
				u.save();
			}
			*/
		}
	}

}
