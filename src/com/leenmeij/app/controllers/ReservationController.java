package com.leenmeij.app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.leenmeij.app.models.Invoice;
import com.leenmeij.app.models.Reservation;
import com.leenmeij.app.models.User;
import com.leenmeij.app.models.Vehicle;
import com.leenmeij.app.models.VehicleDamage;
import com.leenmeij.app.models.VehicleOption;
import com.leenmeij.app.utils.CreatePdf;
import com.leenmeij.app.views.reservations.AddReservation;
import com.leenmeij.app.views.reservations.EditReservation;
import com.leenmeij.app.views.reservations.SelectCustomer;
import com.leenmeij.app.views.reservations.SelectVehicle;
import com.leenmeij.app.views.reservations.ViewReservation;
import com.leenmeij.app.views.vehicle.AddDamage;

/**
 * Here we handle showing screens, actionlistners and other events
 * We also make a few tables, and calculations for prices and such
 * @author Deam Kop (s1075228)
 *
 */
public class ReservationController implements ActionListener, WindowListener{

	// Declare the views
	private AddReservation addReservation;
	private ViewReservation viewReservation;
	private EditReservation editReservation;
	private SelectCustomer selectCustomer;
	private SelectVehicle selectVehicle;
	private AddDamage addDamage;
	// Declare a list with options.
	private ArrayList<Integer> optionList;
	// Declare a table we can use in multiple methods
	public static JTable reservationTable;

	// Set the vehicle id for later use
	private int vehicleID = 0; 
	// Make a temporary id, so when another vehicle is selected, this one is set back to available
	private int previousSelectedVehicleID = 0;
	// Set the reservationid for later use
	private int reservationID = 0;
	// Declare the price per day for a vehicle, so we can use it over multiple methods
	private double dayprice = 0;

	/**
	 * Make the view for adding reservations
	 * Declare the actionlisteners, and add a windowlistener
	 * for catching the closingoperation
	 */
	public void getAddReservations() {
		addReservation = new AddReservation();
		addReservation.selectCustomerButton.addActionListener(this);
		addReservation.selectVehicleButton.addActionListener(this);
		addReservation.addButton.addActionListener(this);
		addReservation.setVisible(true);
		// Catch the close event, so we can set the selected vehicle available
		addReservation.addWindowListener(this);
		addOptions();
	}

	/**
	 * Get the view for selecting a customer
	 * Set the table in the view
	 * Set the actionlistener
	 */
	public void getSelectCustomer() {
		selectCustomer = new SelectCustomer();
		selectCustomer.tablePanel.setViewportView(UserController.getCustomerTable());
		selectCustomer.selectButton.addActionListener(this);
		selectCustomer.searchButton.addActionListener(this);
		selectCustomer.setVisible(true);
	}

	/**
	 * Get the view for selecting a vehicle
	 * Set the table in the view
	 * Set the actionlistener
	 */
	public void getSelectVehicle() {
		selectVehicle = new SelectVehicle();
		selectVehicle.tablePanel.setViewportView(VehicleController.getVehicleDateTable(addReservation.startDatePicker.getDate(), addReservation.endDatePicker.getDate()));
		selectVehicle.selectButton.addActionListener(this);
		selectVehicle.setVisible(true);
	}
	
	/**
	 * Get the view for the reservation overview
	 * Set the table in the view
	 * Set the actionlistener
	 */
	public void getViewReservations() {
		viewReservation = new ViewReservation();
		viewReservation.tablePanel.setViewportView(getReservationTable());
		viewReservation.editButton.addActionListener(this);
		viewReservation.setVisible(true);
	}
	
	/**
	 * Set all the information for the editreservation view
	 * Set all the selected options as selected as well.
	 * @param id
	 */
	public void getEditReservation(int id){
		editReservation = new EditReservation();
		editReservation.damageButton.addActionListener(this);
		editReservation.editButton.addActionListener(this);
		editReservation.deleteButton.addActionListener(this);
		
		// Get the reservation information
		Reservation reservation = new Reservation();
		reservation = reservation.getById(id);
		
		// Get the user information
		User user = new User();
		user = user.getById(reservation.getUserId());
		
		// Get the vehicle information
		Vehicle vehicle = new Vehicle();
		vehicle = vehicle.getById(reservation.getVehicleId());
		
		// Set the text accordingly
		editReservation.reservationField.setText(Integer.toString(reservation.getId()));
		editReservation.userField.setText(user.getFirstName() + " " + user.getLastName());
		editReservation.vehicleField.setText(vehicle.getBrand() + " " + vehicle.getModel());
		editReservation.rentalDatesTextField.setText(reservation.getStartDate() + " t/m " + reservation.getEndDate());
		
		editReservation.setVisible(true);
		
		optionList = new ArrayList<>();
		
		// Set the selected vehicleoptions
		VehicleOption options = new VehicleOption();
		for (final VehicleOption vo : options.all()) {
			// Set the checkbox variables
			final JCheckBox checkBox = new JCheckBox();
			checkBox.setText(vo.getName());
			checkBox.setName(Integer.toString(vo.getId()));
			
			// for each vehicle option that we allready have
			// set the checkbox checked
			for (Integer o : vo.all(id)) {
				if (o == vo.getId()) {
					checkBox.setSelected(true);
					optionList.add(vo.getId());
				}
			}
			
			// If there is a change in the number of items add it to the list
			// add the item to the list
			checkBox.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// a checkbox is selected, add the item to the list
					if (checkBox.isSelected()) {
						optionList.add(vo.getId());
					}else{
						// if it is deselected, remove it from the list
						for (int i = 0; i < optionList.size(); i++) {
							int tempId = optionList.get(i);
							
							if (tempId == vo.getId()) {
								optionList.remove(i);
							}
						}
					}
				}
			});
			
			editReservation.optionsPanel.add(checkBox);
		}
	}
	
	/**
	 * Show the view for adding damage to a vehicle
	 * Declare the actionlisteners and set the text to the textfields
	 * @param id
	 */
	public void showAddDamage(int id){
		// Declare the view
		addDamage = new AddDamage();
		addDamage.addButton.addActionListener(this);
		
		// Declare a reservationmodel
		Reservation reservation = new Reservation();
		reservation = reservation.getById(id);
		
		// Declare a vehiclemodel
		Vehicle vehicle = new Vehicle();
		vehicle = vehicle.getById(reservation.getVehicleId());
		
		// Set a hidden integer for later use
		addDamage.vehicleID = vehicle.getId();
		// Set the vehicle make and model
		addDamage.vehicleTextField.setText(vehicle.getBrand() + " " + vehicle.getModel());
		// Set the reservation id
		addDamage.reservationTextField.setText(Integer.toString(reservation.getId()));
		// Set the screen visible
		addDamage.setVisible(true);
	}

	/**
	 * Add the options to the panel where it is initiated
	 */
	private void addOptions() {
		// Declare the model
		VehicleOption options = new VehicleOption();
		// Declare the arraylist
		optionList = new ArrayList<Integer>();

		// Foreach vehicleoption in the arraylist from the model,
		// create a checkbox
		for (final VehicleOption vo : options.all()) {
			// Make a new checkboxs
			final JCheckBox checkBox = new JCheckBox();
			// Set the checkboxtext
			checkBox.setText(vo.getName());
			// Add an itemlistner for the checkbox
			checkBox.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// If the checkbox is selecten, add it to the list
					// and update the prices
					if (checkBox.isSelected()) {
						optionList.add(vo.getId());
						updatePrices(dayprice += vo.getPrice() * 24);
					}
					// If the checkbox is deselected
					// Update the list, and update the price
					else 
					{
						for (int i = 0; i < optionList.size(); i++) {
							int tempId = optionList.get(i);
							
							if (tempId == vo.getId()) {
								optionList.remove(i);
								updatePrices(dayprice -= vo.getPrice() * 24);
							}
						}
					}
				}
			});
			// Add the checkbox to the panel.
			addReservation.vehicleOptionsPanel.add(checkBox);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * Add reservation actionlistners
		 */
		if (addReservation != null) {
			if (e.getSource() == addReservation.selectCustomerButton) {
				getSelectCustomer();
			}

			// Check if there are dates selected
			else if (e.getSource() == addReservation.selectVehicleButton) {
				if (addReservation.startDatePicker.getDate() == null
						|| addReservation.endDatePicker.getDate() == null 
							|| addReservation.startDatePicker.getDate().equals(addReservation.endDatePicker.getDate())
								|| addReservation.endDatePicker.getDate().getTime() < addReservation.startDatePicker.getDate().getTime()) {
					JOptionPane
							.showMessageDialog(null,
									"Selecteer eerst een begin en einddatum voor de verhuur.");
				} else {
					getSelectVehicle();
				}
			}
			
			// Insert the reservation and the options into the database
			else if(e.getSource() == addReservation.addButton){
				// Check if a vehicle or customer is selected
				if(addReservation.customerNumberTextField.getText().isEmpty() || addReservation.modelTextField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Selecteer eerst een gebruiker en voertuig");
				} else{
					try{
						// Set the reservation information
						Reservation reservation = new Reservation();
						reservation.setUserId(Integer.parseInt(addReservation.customerNumberTextField.getText()));
						reservation.setVehicleId(vehicleID);
						
						// Parse the date values
						java.sql.Date sqlStartDate = new java.sql.Date(addReservation.startDatePicker.getDate().getTime());
						java.sql.Date sqlEndDate = new java.sql.Date(addReservation.endDatePicker.getDate().getTime());
						
						reservation.setStartDate(sqlStartDate);
						reservation.setEndDate(sqlEndDate);
						
						// Set pickedup
						reservation.setPickedUp(false);
						
						// Insert the reservation
						reservation.Insert(reservation);
					
						// Ask for the reservation id so we can process it
						Reservation r = reservation.get(reservation);
	
						// foreach option in the list, add it to the database
						for (Integer integer : optionList) {
							r.InsertReservationOption(integer, r.getId());
						}
						
						// Create the invoice
						Invoice invoice = new Invoice();
						invoice.setUser_id(Integer.parseInt(addReservation.customerNumberTextField.getText()));
						invoice.setVehicle_id(vehicleID);
						invoice.setStartdate(sqlStartDate);
						invoice.setEnddate(sqlEndDate);
						
						// Calculate the prices we need
						Vehicle vehicle = new Vehicle();
						vehicle = vehicle.getById(vehicleID);
						
						// Set the price per day
						double dayPrice = vehicle.getHourlyrate() * 24;
						// Set the time per day
						int day = 24*60*60*1000;
						// Calculate the number of days
						long days = Math.abs((addReservation.startDatePicker.getDate().getTime() - addReservation.endDatePicker.getDate().getTime()) / day);
						
						// Set additional invoice information
						invoice.setPrice(dayPrice * days);
						invoice.setTotal((invoice.getPrice()) + ((invoice.getPrice() / 100) * 21));
						invoice.setReservation_id(r.getId());
						invoice.Insert(invoice);
						
						try {
							// Create the invoice pdf
							CreatePdf pdf = new CreatePdf();
							pdf.createPdf(invoice);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Door een systeemfout kon er geen pdf gegenereerd worden.");
						}
						
						// Show messagedialog with succes
						JOptionPane.showMessageDialog(null, "De reservering is succesvol aangemaakt.");
						
						// Close the window
						addReservation.dispose();
						
						vehicle.setVehicleAvailable(vehicleID, false);
						
						MainController.update();
					} catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Er missen gegevens, controleer de invoer het probeer het opnieuw");
					}
				}
				
			}
		}
		/**
		 * Customer selection actionlistner
		 */
		if (selectCustomer != null) {
			if (e.getSource() == selectCustomer.searchButton) {
				// Clear the existing table
				selectCustomer.tablePanel.remove(UserController.getCustomerTable());
				// Add a new table with the results from the search
				selectCustomer.tablePanel
						.add(UserController.getSearchCustomerTable(selectCustomer.searchTextField
								.getText()));
				// Set the viewport of the scrollpanel to the table so we can see the headers.
				selectCustomer.tablePanel
						.setViewportView(UserController.getSearchCustomerTable(selectCustomer.searchTextField
								.getText()));
			}

			else if (e.getSource() == selectCustomer.selectButton) {
				// Set the text in the addreservation view, so we can process it
				// later
				addReservation.customerNumberTextField.setText(UserController.customerTable
						.getModel()
						.getValueAt(UserController.customerTable.getSelectedRow(), 0)
						.toString());
				addReservation.firstnameTextField.setText(UserController.customerTable
						.getModel()
						.getValueAt(UserController.customerTable.getSelectedRow(), 1)
						.toString());
				addReservation.lastnameTextField.setText(UserController.customerTable
						.getModel()
						.getValueAt(UserController.customerTable.getSelectedRow(), 2)
						.toString());
				// Dispose the view
				selectCustomer.dispose();
			}
		}

		/**
		 * Vehicle selection actionlistner
		 */
		if (selectVehicle != null) {
			
			if (e.getSource() == selectVehicle.selectButton) {
				Vehicle vehicle = new Vehicle();
				
				vehicleID = Integer.parseInt(VehicleController.vehicleTable.getModel().getValueAt(VehicleController.vehicleTable.getSelectedRow(), 0).toString());

				// If the selected vehicle is locked, return a messagedialog
				if (!vehicle.checkAvailability(vehicleID)) {
					JOptionPane
							.showMessageDialog(null,
									"Dit voertuig wordt op dit moment door iemand anders gehuurd.");
				}else {
					
					// Check if there already was a vehicle selected, if nog set te
					// previousselectedvehicleid
					// if there allready was a vehicle selected, make sure this
					// vehicle
					// is not locked anymore
					if (previousSelectedVehicleID != 0) {
						vehicle.setVehicleAvailable(previousSelectedVehicleID,
								false);
					} else {
						previousSelectedVehicleID = Integer.parseInt(VehicleController.vehicleTable
								.getModel()
								.getValueAt(VehicleController.vehicleTable.getSelectedRow(), 0)
								.toString());
					}
	
	
					// Set a variable for the vehicleID, so we can handle it with making the reservation
					vehicleID = Integer.parseInt(VehicleController.vehicleTable.getModel()
							.getValueAt(VehicleController.vehicleTable.getSelectedRow(), 0)
							.toString());
					// Set the information of the vehicle to the textboxes
					addReservation.brandTextField.setText(VehicleController.vehicleTable.getModel()
							.getValueAt(VehicleController.vehicleTable.getSelectedRow(), 1)
							.toString());
					addReservation.modelTextField.setText(VehicleController.vehicleTable.getModel()
							.getValueAt(VehicleController.vehicleTable.getSelectedRow(), 2)
							.toString());
					addReservation.licenseTextField.setText(VehicleController.vehicleTable.getModel()
							.getValueAt(VehicleController.vehicleTable.getSelectedRow(), 3)
							.toString());
	
					// Close the screen
					selectVehicle.dispose();
					vehicle.setVehicleAvailable(vehicleID, true);
					
					// Get the vehicle information
					vehicle = vehicle.getById(vehicleID);
					
					// Set the price per day
					dayprice = vehicle.getHourlyrate() * 24;
					
					updatePrices(0);
				}
				
			}
		}
		
		/**
		 * Reservation overview actionlistner
		 */
		if(viewReservation != null){
			if(e.getSource() == viewReservation.editButton){
				try {
					getEditReservation(Integer.parseInt(reservationTable.getModel()
							.getValueAt(reservationTable.getSelectedRow(), 0)
							.toString()));
					reservationID = Integer.parseInt(reservationTable.getModel()
							.getValueAt(reservationTable.getSelectedRow(), 0)
							.toString());
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Selecteer een reservering", "Fout", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		/**
		 * Reservation edit actionlistner
		 */
		if(editReservation != null){
			if(e.getSource() == editReservation.damageButton){
				showAddDamage(reservationID);
			}
			
			else if(e.getSource() == editReservation.editButton){
				// Get the reservationmodel
				Reservation reservation = new Reservation();
				reservation = reservation.getById(Integer.parseInt(editReservation.reservationField.getText()));
				
				// Delete the existing options
				VehicleOption vehicleOption = new VehicleOption();
				vehicleOption.DeleteReservationOption(reservation.getId());
			
				// Insert the options
				for (Integer integer : optionList) {
					reservation.InsertReservationOption(integer, reservation.getId());
				}
				// SHow a message
				JOptionPane.showMessageDialog(null, "De reservering is succesvol bijgewerkt.");
				// Dispose the window
				editReservation.dispose();
				// Update the table
				viewReservation.tablePanel.setViewportView(getReservationTable());
			}
			
			else if(e.getSource() == editReservation.deleteButton){
				try {
					// Declare the model
					Reservation reservation = new Reservation();
					// Delete the reservation
					reservation.Delete(Integer.parseInt(editReservation.reservationField.getText()));
					// SHow a message
					JOptionPane.showMessageDialog(null, "De reservering is geannuleerd.");
					// Dispose the view
					editReservation.dispose();
					// Update the table
					viewReservation.tablePanel.setViewportView(getReservationTable());
				} catch (NumberFormatException e1) {
					// If there is an error, show a message
					JOptionPane.showMessageDialog(null, "De geselecteerde reserving kan niet worden verwijdert.");
				}
			}
		}
		
		/**
		 * Add damage actionlistner
		 */
		if(addDamage != null){
			if(e.getSource() == addDamage.addButton){
				try{
					VehicleDamage vehicleDamage = new VehicleDamage();				
					vehicleDamage.setVehicleID(addDamage.vehicleID);
					vehicleDamage.setReservationID(Integer.parseInt(addDamage.reservationTextField.getText()));
					// Get todays date
				    Date date = new Date();
				    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					
					vehicleDamage.setInsertDate(sqlDate);
					vehicleDamage.setDamage(addDamage.textArea.getText());
					vehicleDamage.Insert(vehicleDamage);
					// Show a succes message
					JOptionPane.showMessageDialog(null, "De schade is succesvol gemeld");
					
					if(addDamage.availableCheckBox.isSelected()){
						Vehicle vehicle = new Vehicle();
						vehicle.setVehicleAvailable(addDamage.vehicleID, true);
					}
					
					addDamage.dispose();
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, probeer het later nog eens of neem contact op met de systeembeheerder");
				}
			}
		}
	}

	/**
	 * Make the reservationtable for showing the reservation information
	 */
	public static JTable getReservationTable() {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Klant");
		columnNames.add("Voertuig");

		// Set the userdata for the columnNames
		Vector<Vector<String>> reservationList = new Vector<Vector<String>>();
		Reservation reservation = new Reservation();
		
		
		// Foreach vehicle in the list, add it to the vector
		for (Reservation r : reservation.all()) {
			Vector<String> data = new Vector<>();
			
			Vehicle vehicle = new Vehicle();
			vehicle = vehicle.getById(r.getVehicleId());
			
			User user = new User();
			user = user.getById(r.getUserId());
			
			// Set the data we want to show
			data.add(Integer.toString(r.getId()));
			data.add(user.getFirstName() + " " + user.getLastName());
			data.add(vehicle.getBrand() + " " + vehicle.getModel());

			// Add the data to the list
			reservationList.add(data);
		}

		// Create the new JTable
		reservationTable = new JTable(reservationList, columnNames);
		// Set the selection mode to one row only
		reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return reservationTable;
	}
	
	/**
	 * Make the reservationtable for showing the last 15 reservations
	 */
	public static JTable getLatestReservationTable() {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Klant");
		columnNames.add("Voertuig");

		// Set the userdata for the columnNames
		Vector<Vector<String>> reservationList = new Vector<Vector<String>>();
		Reservation reservation = new Reservation();
		
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		
		
		// Foreach vehicle in the list, add it to the vector
		for (Reservation r : reservation.latest(sdf.format(cal.getTime()))) {
			Vector<String> data = new Vector<>();
			
			Vehicle vehicle = new Vehicle();
			vehicle = vehicle.getById(r.getVehicleId());
			
			User user = new User();
			user = user.getById(r.getUserId());
			
			// Set the data we want to show
			data.add(Integer.toString(r.getId()));
			data.add(user.getFirstName() + " " + user.getLastName());
			data.add(vehicle.getBrand() + " " + vehicle.getModel());

			// Add the data to the list
			reservationList.add(data);
		}

		// Create the new JTable
		reservationTable = new JTable(reservationList, columnNames);
		// Set the selection mode to one row only
		reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return reservationTable;
	}
	
	/**
	 * Calculate the prices depending on the days of rental
	 * @param option
	 */
	private void updatePrices(double option){
		// Initiate the parser
		DecimalFormat format = new DecimalFormat("#.00");
		
		// Set the time per day
		int day = 24*60*60*1000;
		// Calculate the number of days
		long days = Math.abs((addReservation.startDatePicker.getDate().getTime() - addReservation.endDatePicker.getDate().getTime()) / day);
		
		double price = (dayprice) * days;
		
		addReservation.priceTextField.setText("€ " + format.format(price));
		addReservation.vatTextField.setText("€ " + format.format(price / 100 * 21));
		addReservation.totalTextField.setText("€ " + format.format(price + ((price) / 100 * 21)));

	}

	/**
	 * Window events for setting vehicles available after aborting
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleAvailable(vehicleID, false);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
