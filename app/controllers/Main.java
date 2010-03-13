package controllers;

public class Main extends Application {
	
	@Secure
	public static void index() {
        render();
	}
	
	// TEMP (move to a new a class)
	public static void profile() {
        render();
	}
	
	// TEMP (move to a new a class)
	public static void findFriends() {
        render();
	}
}
