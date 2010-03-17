package models;


public class UserInfo {
    public Long id;
    
    public String email;
    
    public boolean isActivate;
    public boolean isAdmin;
    public boolean isFriend;
    public boolean isInvited;
    public boolean hasRequested;
    
    public String firstname;
    public String lastname;
    public String address;
    public String phone;
    
    public UserInfo(User user, User me) {
      id = user.id;
      email = user.email;
      isActivate = user.isActivate;
      isAdmin = user.isAdmin;
      firstname = user.firstname;
      lastname = user.lastname;
      address = user.address;
      phone = user.phone;
      isFriend = false;
      isFriend = (me.friends.indexOf(user)!=-1);
      FriendRequest fr = FriendRequest.findBy2Users(user, me);
      isInvited = (fr!=null && fr.caller.id==me.id);
      hasRequested = (fr!=null && fr.concerned.id==me.id);
    }
}
