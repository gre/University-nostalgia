package models;


public class UserInfo {
    public Long id;
    
    public String email;
    
    public boolean isActivate;
    public boolean isAdmin;
    
    public String firstname;
    public String lastname;
    public String address;
    public String phone;
    
    public UserInfo(User user) {
        id = user.id;
        email = user.email;
        isActivate = user.isActivate;
        isAdmin = user.isAdmin;
        firstname = user.firstname;
        lastname = user.lastname;
        address = user.address;
        phone = user.phone;
    }
}
