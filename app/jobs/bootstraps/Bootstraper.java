package jobs.bootstraps;

import play.jobs.*;
import models.*;
import play.test.Fixtures;
 
@OnApplicationStart
public class Bootstraper extends Job {
    
    public void doJob() {
      Fixtures.deleteAll();
      Fixtures.load("jobs/bootstraps/diploma.yml");
      Fixtures.load("jobs/bootstraps/user.yml");
    }
    
}
