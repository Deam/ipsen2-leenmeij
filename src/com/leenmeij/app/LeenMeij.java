package com.leenmeij.app;

import com.leenmeij.app.controllers.MainController;

public class LeenMeij {
	
	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		
		// Try to set the style of the frames, catch the exeptions
		MainController controller = new MainController();
		controller.showMainView();
	}
}
