package com.leenmeij.app;

import com.leenmeij.app.controllers.UserController;

public class LeenMeij {
	
	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		
		// Try to set the style of the frames, catch the exeptions
		UserController controller = new UserController();
		controller.initLogin();
	}
}
