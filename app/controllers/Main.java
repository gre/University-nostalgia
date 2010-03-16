package controllers;

public class Main extends Secure {
	
	public static void index() {
        flash.keep();
        Friends.list();
	}
}
