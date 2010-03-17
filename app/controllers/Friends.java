package controllers;
import models.*;
import play.data.validation.*;
import java.util.*;

public class Friends extends Secure {
  
	public static void list() {
    User connect = connectedUser();
    List<UserInfo> friends = new ArrayList<UserInfo>();
    for(User friend : connect.friends)
      friends.add(new UserInfo(friend, connect));
		List<UserInfo> requests = new ArrayList<UserInfo>();
		for(FriendRequest request : FriendRequest.findRequestFor(connect))
			requests.add(new UserInfo(request.caller, connect));
    render(friends, requests);
	}
  
	public static void inviteOrAccept(@Required Long id) {
		if(Validation.hasErrors())
			forbidden();
    User connect = connectedUser();
		for(User u : connect.friends)
			if(u.id==id)
				error("alreadyFriend");
		User user = User.findById(id);
		notFoundIfNull(user);
		FriendRequest fr = FriendRequest.findBy2Users(connect, user);
		if(fr==null)
			fr = new FriendRequest(connect, user).save();
		if(fr.concerned.id==connect.id) {
			fr.delete();
			user.friends.add(connect);
			user.save();
			connect.friends.add(user);
			connect.save();
		}
		if(request.format.contains("json"))
			renderJSON("{}");
		list();
	}
  
	public static void delete(Long id) {
    render();
	}
}
