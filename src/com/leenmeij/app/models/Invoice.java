package com.leenmeij.app.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

/**
 * Vehicle invoices are made in this class.
 * The invoice holds the user and vehicle id, and the price
 * aswell as the dates and additional information
 * @author Deam Kop (s1075228), Michiel Mooring, David
 *
 */
public class Invoice {

	/**
	 * Private model values
	 */
	private int id;
	private int user_id;
	private int vehicle_id;
	private Date startdate;
	private Date enddate;
	private Double price;
	private Double total;
	private int reservation_id;
	
	
	/**
	 * Insert the invoice in the database
	 * @param invoice
	 */
	public void Insert(Invoice invoice){
		try {
			Database database = new Database();
			database.connect();
			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"INSERT INTO invoices(user_id, vehicle_id, startdate, enddate, price, total, reservation_id) VALUES(?, ?, ?, ?, ?, ?, ?)");

			// Set the reservation information
			statement.setInt(1, invoice.user_id);
			statement.setInt(2, invoice.vehicle_id);
			
			// Format the date so we can insert it in the right format
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
					
			statement.setString(3, format.format(invoice.startdate));
			statement.setString(4, format.format(invoice.enddate));
			
			statement.setDouble(5, invoice.price);
			statement.setDouble(6, total);

			statement.setInt(7, invoice.reservation_id);


			// Execute the query
			statement.executeUpdate();

			// Close the connection
			database.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Return a list with all the invoices
	 * @return a list filled with the invoice information
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Invoice> all(){
		// Declare a list for returning
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		
		try {
			// Connect to the database
			Database database = new Database();
			database.connect();
			// Create the statement
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM invoices");
			// Declare the resultset
			ResultSet set = statement.executeQuery();
			
			while (set.next()) {
				// Declare a new model
				Invoice	invoice = new Invoice();
				// Set the model values
				invoice.setId(set.getInt("id"));
				invoice.setUser_id(set.getInt("user_id"));
				invoice.setVehicle_id(set.getInt("vehicle_id"));
				
				invoice.setStartdate(new java.sql.Date(new java.util.Date(set.getString("startdate")).getTime()));
				invoice.setEnddate(new java.sql.Date(new java.util.Date(set.getString("enddate")).getTime()));
				
				invoice.setPrice(set.getDouble("price"));
				invoice.setTotal(set.getDouble("total"));
				
				invoice.setReservation_id(set.getInt("reservation_id"));
				// Add the information to the arraylist
				invoiceList.add(invoice);
			}
			
			// close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the list
		return invoiceList;
	}

	
	/**
	 * Get the last 15 entries from invoices
	 * @return a arraylist with the invoice information
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Invoice> latest(){
		// Declare a new arraylist for returning
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		
		try {
			// Connect to the database
			Database database = new Database();
			database.connect();
			// Create the statement
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM invoices ORDER BY id DESC LIMIT 15");
			// Declare the resultset
			ResultSet set = statement.executeQuery();
			
			while (set.next()) {
				// Declare a new model
				Invoice	invoice = new Invoice();
				// Set the model values
				invoice.setId(set.getInt("id"));
				invoice.setUser_id(set.getInt("user_id"));
				invoice.setVehicle_id(set.getInt("vehicle_id"));
				
				invoice.setStartdate(new java.sql.Date(new java.util.Date(set.getString("startdate")).getTime()));
				invoice.setEnddate(new java.sql.Date(new java.util.Date(set.getString("enddate")).getTime()));
				
				invoice.setPrice(set.getDouble("price"));
				invoice.setTotal(set.getDouble("total"));
				
				invoice.setReservation_id(set.getInt("reservation_id"));
				// Add the information to the list
				invoiceList.add(invoice);
			}
			
			// close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the list
		return invoiceList;
	}


	/**
	 * Get the invoive id
	 * @return the invoice id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Set the invoice id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * get the userid
	 * @return the user id for the invoice
	 */
	public int getUser_id() {
		return user_id;
	}
	
	/**
	 * Set the user id for the invoice
	 * @param user_id
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * Get the vehicle id
	 * @return the vehicle id for the invoice
	 */
	public int getVehicle_id() {
		return vehicle_id;
	}
	
	/**
	 * Set the vehicle id for the invoice
	 * @param vehicle_id
	 */
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	
	/**
	 * Get the starting date
	 * @return the starting date for the invoice
	 */
	public Date getStartdate() {
		return startdate;
	}
	
	/**
	 * Set the starting date for the invoice
	 * @param startdate
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	/**
	 * Get the ending date
	 * @return the enddate for the invoice
	 */
	public Date getEnddate() {
		return enddate;
	}
	
	/**
	 * Set the enddate for the invoice
	 * @param enddate
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	/**
	 * Get the price
	 * @return the price without vat for the invoice
	 */
	public Double getPrice() {
		return price;
	}
	
	/**
	 * Set the price for the invoice
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * Get the total price (price including VAT)
	 * @return the total prive for the invoice
	 */
	public Double getTotal() {
		return total;
	}
	
	/**
	 * Set the totalprice for the invoice
	 * @param total
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * Get the reservation id
	 * @return the reservation id for the invoice
	 */
	public int getReservation_id() {
		return reservation_id;
	}
	
	/**
	 * Set the reservationid for the invoice
	 * @param reservation_id
	 */
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
}