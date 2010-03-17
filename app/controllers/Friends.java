package controllers;
import models.*;

import java.util.*;

public class Friends extends Secure {
  
	public static void list() {
    User connect = connectedUser();
    List<UserInfo> friends = new ArrayList<UserInfo>();
    for(User friend : connect.friends)
      friends.add(new UserInfo(friend).setFriend(friend.friends.indexOf(connect)!=-1));
    render(friends);
	}
  
	public static void inviteOrAccept(Long id) {
    render();
	}
  
	public static void delete(Long id) {
    render();
	}
}
