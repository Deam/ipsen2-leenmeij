package com.leenmeij.app;

import javax.swing.JOptionPane;

import com.leenmeij.app.controllers.UserController;

/**
 * The main startup class for the application
 * @author Deam Kop (s1075228)
 *
 */
public class LeenMeij {
	
	public static void main(String[] args) {
		// Set the menubar to the top for the Mac users
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		
		// Try to open the login screen
		try {
			UserController controller = new UserController();
			controller.initLogin();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "De applicatie kan niet opgestart worden. Neem contact op met de systeembeheerder");
		}
	}
}
