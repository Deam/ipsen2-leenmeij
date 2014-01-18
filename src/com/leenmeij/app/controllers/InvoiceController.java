package com.leenmeij.app.controllers;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.leenmeij.app.models.Invoice;
import com.leenmeij.app.models.User;
import com.leenmeij.app.models.Vehicle;
import com.leenmeij.app.views.reservations.InvoiceOverview;

/**
 * Invoicecontroller handles the showing of the overview
 * as wel as creating the desired tables
 * @author Deam Kop (s1075228)
 */
public class InvoiceController {
	
	private static JTable invoiceTable;
	
	private InvoiceOverview overview;
	
	/**
	 * Declare the overview for showing
	 */
	public void getOverview(){
		overview = new InvoiceOverview();
		overview.scrollPane.setViewportView(getInvoiceTable());
		overview.setVisible(true);
	}
	
	/**
	 * Make a static JTable so we can get it in any class we want
	 * @return A JTable filled with information about the invoices.
	 */
	public static JTable getInvoiceTable() {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Klant");
		columnNames.add("Voertuig");
		columnNames.add("Startdatum");
		columnNames.add("Einddatum");
		columnNames.add("Prijs");
		columnNames.add("Totaal");

		// Set the userdata for the columnNames
		Vector<Vector<String>> invoiceList = new Vector<Vector<String>>();
		Invoice invoice = new Invoice();
		for (Invoice v : invoice.all()) {
			Vector<String> data = new Vector<>();
			// Add the invoice id
			data.add(Integer.toString(v.getId()));
			
			// Get the user information
			User user = new User();
			user = user.getById(v.getUser_id());
			// Insert the user information
			data.add(user.getFirstName() + " " + user.getLastName());
			
			// Get the vehicledata
			Vehicle vehicle = new Vehicle();
			vehicle = vehicle.getById(v.getVehicle_id());
			// Insert the vehicledata to the list
			data.add(vehicle.getBrand() + " " + vehicle.getModel());
			
			// Set the formatters
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
			DecimalFormat doubleFormatter = new DecimalFormat("#.00");

			data.add(format.format(v.getStartdate()));
			data.add(format.format(v.getEnddate()));
			data.add("€ " + doubleFormatter.format(v.getPrice()));
			data.add("€ " + doubleFormatter.format(v.getTotal()));

			invoiceList.add(data);
		}

		// Create the new JTable
		invoiceTable = new JTable(invoiceList, columnNames);
		// Set the selection mode to one row only
		invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return invoiceTable;
	}
	
	/**
	 * Make a static JTable so we can get it in any class we want
	 * @return A JTable filled with information about the last 15 invoices.
	 */
	public static JTable getLatestInvoiceTable() {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Klant");
		columnNames.add("Voertuig");
		columnNames.add("Startdatum");
		columnNames.add("Einddatum");
		columnNames.add("Prijs");
		columnNames.add("Totaal");

		// Set the userdata for the columnNames
		Vector<Vector<String>> invoiceList = new Vector<Vector<String>>();
		Invoice invoice = new Invoice();
		for (Invoice v : invoice.latest()) {
			Vector<String> data = new Vector<>();

			data.add(Integer.toString(v.getId()));
			
			// Get the user information
			User user = new User();
			user = user.getById(v.getUser_id());
			// Insert the user information
			data.add(user.getFirstName() + " " + user.getLastName());
			
			// Get the vehicledata
			Vehicle vehicle = new Vehicle();
			vehicle = vehicle.getById(v.getVehicle_id());
			// Insert the vehicledata to the list
			data.add(vehicle.getBrand() + " " + vehicle.getModel());
			
			// Set the formatters
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
			DecimalFormat doubleFormatter = new DecimalFormat("#.00");
			
			data.add(format.format(v.getStartdate()));
			data.add(format.format(v.getEnddate()));
			data.add("€ " + doubleFormatter.format(v.getPrice()));
			data.add("€ " + doubleFormatter.format(v.getTotal()));

			invoiceList.add(data);
		}

		// Create the new JTable
		invoiceTable = new JTable(invoiceList, columnNames);
		// Set the selection mode to one row only
		invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return invoiceTable;
	}
}
