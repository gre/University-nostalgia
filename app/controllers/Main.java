package controllers;

public class Main extends Application {
	
	@Secure
	public static void index() {
		render();
	}
}
