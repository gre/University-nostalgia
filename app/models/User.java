package models;

import javax.persistence.*; 

import play.db.helper.SqlQuery;
import play.db.helper.SqlSelect;
import play.db.jpa.*;
import play.data.validation.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends Model {
  
  @Email
  @Required
  public String email;
  
  @Required
  public String password;
  
  public boolean isActivate;
  public boolean isAdmin;
  
  @ManyToMany
  @JoinTable(name="USER_FRIEND")
  public List<User> friends = new ArrayList<User>();

  @ManyToMany(mappedBy = "friends")
  List<User> friendsOf = new ArrayList<User>();

  @Required
  public String firstname;
  
  @Required
  public String lastname;
  
  public String address;
  public String phone;
  
  public Long avatarRevision;
  
  public String getAddress() {
	return address;
  }
	
	public User() {
    isActivate = false;
    isAdmin = false;
    friends = new ArrayList<User>();
    avatarRevision = 0L;
	}
	
  public User(String email, String password, String firstname, String lastname) {
		super();
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

  public boolean checkPassword(String pwd) {
		return password.equals(pwd);
  }
  
  public boolean isAdmin() {
	  return isAdmin;
  }
  public boolean isActivate() {
	  return isActivate;
  }
  public void activate() {
	  isActivate = true;
  }
  
  public static void makeFriends(User u1, User u2) {
    if(u1.id != u2.id && u1.friends.indexOf(u2)==-1) {
      u1.friends.add(u2);
      u2.friends.add(u1);
      u1.save();
      u2.save();
    }
  }
  
  public static User findByEmail(String email) {
	  return find("email = ?", email).first();
  }
	
	public static List<User> findBySearch(String search) {
		List<User> users = new ArrayList<User>();
		String[] words = search.split("[ ]+");
		
		String query = "";
		for(String word : words) {
			if(!query.equals(""))
				query += " and ";
      String wLike = SqlQuery.inlineParam("%"+word+"%");
      String wExact = SqlQuery.inlineParam(word);
			query += "(email ="+wExact+" or lower(firstname) like lower("+wLike+") or lower(lastname) like lower("+wLike+") )";
		}
		
		users = find(query).fetch();
		return users;
	}
    public static List<User> getInactiveUsers() {
        return User.find("isActivate = false").fetch();
    }
}
