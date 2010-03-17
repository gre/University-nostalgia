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
  
  @OneToMany
  public List<User> friends;

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
  
  public String getAddress() {
	return address;
  }
	
	public User() {
		isActivate = false;
		isAdmin = false;
    universities = new ArrayList<UniversityYear>();
    corporations = new ArrayList<Corporation>();
		friends = new ArrayList<User>();
		
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
			String w = SqlQuery.inlineParam("%"+word+"%");
			query += "(email like "+w+" or firstname like "+w+" or lastname like "+w+" )";
		}
		
		users = find(query).fetch();
		return users;
	}
}
