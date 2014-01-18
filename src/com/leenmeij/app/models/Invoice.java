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
 * @author Deam
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
	 *  Database methods
	 */
	
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
	 * @return
	 */
	public ArrayList<Invoice> all(){
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		
		try {
			Database database = new Database();
			database.connect();
			
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM invoices");
			
			ResultSet set = statement.executeQuery();
			
			while (set.next()) {
				Invoice	invoice = new Invoice();
				invoice.setId(set.getInt("id"));
				invoice.setUser_id(set.getInt("user_id"));
				invoice.setVehicle_id(set.getInt("vehicle_id"));
				
				// Format the dates
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
				invoice.setStartdate(new java.sql.Date(format.parse(set.getString("startdate")).getTime()));
				invoice.setEnddate(new java.sql.Date(format.parse(set.getString("startdate")).getTime()));
				
				invoice.setPrice(set.getDouble("price"));
				invoice.setTotal(set.getDouble("total"));
				
				invoice.setReservation_id(set.getInt("reservation_id"));
				
				invoiceList.add(invoice);
			}
			
			// close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return invoiceList;
	}

	
	/**
	 * Return the last 15 invoices
	 * @return
	 */
	public ArrayList<Invoice> latest(){
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		
		try {
			Database database = new Database();
			database.connect();
			
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM invoices ORDER BY id DESC LIMIT 15");
			
			ResultSet set = statement.executeQuery();
			
			while (set.next()) {
				Invoice	invoice = new Invoice();
				invoice.setId(set.getInt("id"));
				invoice.setUser_id(set.getInt("user_id"));
				invoice.setVehicle_id(set.getInt("vehicle_id"));
				
				// Format the dates
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
				invoice.setStartdate(new java.sql.Date(format.parse(set.getString("startdate")).getTime()));
				invoice.setEnddate(new java.sql.Date(format.parse(set.getString("startdate")).getTime()));
				
				invoice.setPrice(set.getDouble("price"));
				invoice.setTotal(set.getDouble("total"));
				
				invoice.setReservation_id(set.getInt("reservation_id"));
				
				invoiceList.add(invoice);
			}
			
			// close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return invoiceList;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Setters and getters
	 * @return
	 */
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
}