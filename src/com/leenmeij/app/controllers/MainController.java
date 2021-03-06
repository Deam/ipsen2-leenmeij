package com.leenmeij.app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.leenmeij.app.models.User;
import com.leenmeij.app.views.Main;
/**
 * Main class, here we show the tables with some information
 * as well as the menubar to select the options from.
 * @author Deam Kop (s1075228), Nick
 */
public class MainController implements ActionListener {

	private static Main main;

	// Declare a new vehicle controller for adding
	private VehicleController vehicleController = new VehicleController();
	private VehicleOptionsController vehicleOptionsController = new VehicleOptionsController();
	private ReservationController reservationController = new ReservationController();
	private UserController userController = new UserController();
	private InvoiceController invoiceController = new InvoiceController();
	// Variable for usermail
	public String userEmail;

	/**
	 * Method to show the mainview
	 * here we also set all the actionlisteners
	 * and tables for viewing
	 */
	public void showMainView() {
		// Declare a new view
		main = new Main(this);
		// Set the loggedin user
		main.loggedLabel.setText("Ingelogd als: " + userEmail);
		// Set the screen visible
		main.setVisible(true);
		
		User user = new User();
		String role = user.getRole(userEmail);
		
		// Disable features for the balie employee
		if(role.equals("Balie")){
			main.vehicleItem.setVisible(false);
		}
		
		// Disable features for the garage employee
		else if (role.equals("Garage")) {
			main.usersItem.setVisible(false);
			main.addReservationItem.setVisible(false);
		}
		main.logoutItem.addActionListener(this);
		main.quitItem.addActionListener(this);
		
		main.addReservationItem.addActionListener(this);
		main.addUserItem.addActionListener(this);
		
		main.vehicleOverviewItem.addActionListener(this);
		main.userOverviewItem.addActionListener(this);
		main.overviewOptionItem.addActionListener(this);
		
		main.viewReservationItem.addActionListener(this);
		
		main.invoiceMenuItem.addActionListener(this);
		
		// Set the tables to the jscrollpanels
		main.usersPanel.setViewportView(UserController.getLatestCustomerTable());
		main.vehiclePanel.setViewportView(VehicleController.getLatestVehicleTable());
		main.reservationsPanel.setViewportView(ReservationController.getLatestReservationTable());
		main.invoicePanel.setViewportView(InvoiceController.getLatestInvoiceTable());
	}
	
	/**
	 * Actionlisteners for the mainview
	 * These are for the menuitems
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == main.addVehicleItem) {
			vehicleController.getAddVehicle();
		}

		else if (e.getSource() == main.vehicleOverviewItem) {
			vehicleController.getOverview();
		}

		else if (e.getSource() == main.addOptionItem) {
			vehicleOptionsController.getAddOption();
		}

		else if (e.getSource() == main.overviewOptionItem) {
			vehicleOptionsController.getOverview();
		}

		else if (e.getSource() == main.addReservationItem) {
			reservationController.getAddReservations();
		}
		
		else if(e.getSource() == main.viewReservationItem){
			reservationController.getViewReservations();
		}

		else if (e.getSource() == main.editUserItem) {
			userController.getEdit(1);
		}
		
		else if (e.getSource() == main.addUserItem) {
			userController.getAdd();
		}
		
		else if(e.getSource() == main.userOverviewItem){
			userController.getOverview();
		}
		
		else if(e.getSource() == main.invoiceMenuItem){
			invoiceController.getOverview();
		}
		
		else if(e.getSource() == main.logoutItem){
			main.dispose();
			userController.initLogin();
		}
		
		else if(e.getSource() == main.quitItem){
			main.dispose();
		}
	}
	
	/**
	 * Update all the tables in the main view when something happens
	 */
	public static void update(){		
		main.usersPanel.setViewportView(UserController.getLatestCustomerTable());
		main.vehiclePanel.setViewportView(VehicleController.getLatestVehicleTable());
		main.reservationsPanel.setViewportView(ReservationController.getLatestReservationTable());
		main.invoicePanel.setViewportView(InvoiceController.getLatestInvoiceTable());
	}
}
