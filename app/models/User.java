package models;

import javax.persistence.*; 
import play.db.jpa.*;
import play.data.validation.*;

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
  public List<User> friends;
  
  @OneToMany
  public List<Message> messages;
  
  @Required
  public String firstname;
  
  @Required
  public String lastname;
  
  @OneToMany
  public List<UniversityYear> universities;
  
  @OneToMany
  public List<Corporation> corporations;
  
  public String address;
  public String phone;
  
	public String getAddress(){
		return address;
	}
	
  public User(String email, String password, String firstname, String lastname) {
		isActivate = false;
		isAdmin = false;
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
  
  public static User findByEmail(String email) {
	  return find("email = ?", email).first();
  }
}
