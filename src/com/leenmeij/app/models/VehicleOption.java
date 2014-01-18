package com.leenmeij.app.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

/**
 * This class handles database methods like:
 * Inserting, updating, deleting and returning lists
 * @author Deam Kop (s1075228)
 *
 */
public class VehicleOption{
	
	private int id;
	private String name;
	private double price;
	
	private ResultSet set = null;
	
	/**
	 * Insert the vehicleoption into the database
	 * @param vehicleOption
	 */
	public void Insert(VehicleOption vehicleOption){
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			
			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement
					("INSERT INTO vehicleoptions(name, price) VALUES(?, ?)");
			
			// Set the vehicle information
			statement.setString(1, vehicleOption.getName());
			statement.setDouble(2, vehicleOption.getPrice());
						
			// Execute the query
			statement.executeUpdate();
			
			// Close the connection
			database.close();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Update a vehicleoption depending on it's ID
	 * 
	 * @param option
	 * @param id
	 */
	public void Update(VehicleOption option, int id){
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			
			// Prepare the update statement
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"UPDATE vehicleoptions SET name = ?, price = ? WHERE id = ?");
			// Set the vehicle information
			statement.setString(1, option.getName());
			statement.setDouble(2, option.getPrice());
			statement.setInt(3, id);
			
			// Execute the query
			statement.executeUpdate();
			
			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete the vehicleoption depending on the reservationid
	 * @param id
	 */
	public void DeleteReservationOption(int id){
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			
			// Create the delete query
			PreparedStatement statement = database.getConnection().prepareStatement("DELETE FROM reservation_vehicle_option WHERE reservation_id = ?");
			
			// Set the id to be deleted
			statement.setInt(1, id);
			
			// Execute statement
			statement.executeUpdate();
			
			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete the vehicleoption depending on the vehicleoption id
	 * @param id
	 */
	public void Delete(int id){
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			
			// Create the delete query
			PreparedStatement statement = database.getConnection().prepareStatement("DELETE FROM vehicleoptions WHERE id = ?");
			
			// Set the id to be deleted
			statement.setInt(1, id);
			
			// Execute statement
			statement.executeUpdate();
			
			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a list with all the vehicleoptions
	 * @return an arraylist with all the vehicleoptions
	 */
	public ArrayList<VehicleOption> all(){
		// Make a new arraylist to hold all the vehicles.
		ArrayList<VehicleOption> options = new ArrayList<>();
		
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			
			// Create the query
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM vehicleoptions");
			
			// Fill the resultset with all entries we get
			set = statement.executeQuery();
			
			// Loop trough all the entries, and create the models
			while(set.next()){
				// Declare a new model
				VehicleOption option = new VehicleOption();
				// Set the retreived information to the model
				option.setId(set.getInt("id"));
				option.setName(set.getString("name"));
				option.setPrice(set.getDouble("price"));
				
				// Add the model to the arraylist
				options.add(option);
			}
			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Return the filled list.
		return options;
	}
	
	/**
	 * Return an ArrayList with all the vehicleoptions that belong to the reservation
	 * depending op the reservation id
	 * @param id
	 * @return a list with the options that belong to a reservation
	 */
	public ArrayList<Integer> all(int id){
		// Make a new arraylist to hold all the vehicles.
		ArrayList<Integer> options = new ArrayList<>();
		
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			
			// Create the query
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM reservation_vehicle_option WHERE reservation_id = ?");
			
			statement.setInt(1, id);
			
			// Fill the resultset with all entries we get
			set = statement.executeQuery();
			
			// Loop trough all the entries, and create the models
			while(set.next()){
				int optionid = set.getInt("vehicle_option_id");
				
				options.add(optionid);
			}
			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Return the filled list.
		return options;
	}
	
	/**
	 * Get the vehicleoption information per given id
	 * @param id
	 * @return an option that belongs to the id
	 */
	public VehicleOption getByID(int id){
		// Declare a new model
		VehicleOption option = new VehicleOption();
		
		// Try to make a connection to the database, and fetch a vehicleoption.
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			
			PreparedStatement statement = database.getConnection().prepareStatement(
					"SELECT * FROM vehicleoptions WHERE id = ?");
			statement.setInt(1, id);

			// Insert statement into resultset
			set = statement.executeQuery();

			// Fill out the customer information
			set.next();
			// option id is the selected id, so no need to get it from the
			// database
			option.setId(id);
			// Get all the additional information
			option.setName(set.getString("name"));
			option.setPrice(set.getDouble("price"));

			// Close the database connection
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return option;
	}

	/**
	 * Get the option id
	 * @return the id of an option
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id of an option
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get te name of an option
	 * @return the name that belong to the option
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the option
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the price of the option
	 * @return the price of the option
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the price of the option
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
