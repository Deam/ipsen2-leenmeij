package com.leenmeij.app.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

/**
 * This class handles the reservation making
 * It holds methods for
 * Inserting, Updating, Deleting etc.
 * @author Deam Kop (s1075228), Michiel Mooring, Nick Bosland
 *
 */
public class Reservation {
	
	private int id;
	private int userId;
	private int vehicleId;
	private Date startDate;
	private Date endDate;
	private boolean status;
	private boolean pickedUp;
	
	/**
	 * Insert the reservation in to the database
	 * @param reservation
	 */
	public void Insert(Reservation reservation) {
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"INSERT INTO reservation(user_id, vehicle_id, startdate, enddate, picked_up, status, sended_review_mail) VALUES(?, ?, ?, ?, ?, ?, ?)");

			// Set the reservation information
			statement.setInt(1, reservation.userId);
			statement.setInt(2, reservation.vehicleId);
			// Format the date
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
			// Set the date
			statement.setString(3, format.format(reservation.startDate));
			statement.setString(4, format.format(reservation.endDate));
			
			// Check if the vehicle is picked up
			int picked = 0;
			if(pickedUp){
				picked = 1;
			} else{
				picked = 0;
			}
			statement.setInt(5, picked);
			
			// Set default values for the status and emailsending
			statement.setInt(6, 0);
			statement.setInt(7, 0);

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Insert a reservation vehicle option in to the database 
	 * with the given optionid and reservationid
	 * @param option_id
	 * @param reservationID
	 */
	public void InsertReservationOption (int option_id, int reservationID) {
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"INSERT INTO reservation_vehicle_option(reservation_id, vehicle_option_id) VALUES(?, ?)");
			// Set the variables we need
			statement.setInt(1, reservationID);
			statement.setInt(2, option_id);


			// Execute the query
			statement.executeUpdate();

			// Close the connection
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Return a single reservation with the given reservation
	 * @param reservation
	 * @return a single reservation
	 */
	public Reservation get(Reservation r){
		// Declare a model for returning
		Reservation reservation = new Reservation();
		
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"SELECT * FROM reservation WHERE user_id = ? AND vehicle_id = ? AND startdate = ? AND enddate = ?");
			// Set the veriables we need
			statement.setInt(1, r.getUserId());
			statement.setInt(2, r.getVehicleId());
			// Format the date
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
			// Set the variables
			statement.setString(3, format.format(r.getStartDate()));
			statement.setString(4, format.format(r.getEndDate()));
			
			// Declare a resultset
			ResultSet set = statement.executeQuery();
			
			// Set the information for the reservation
			while (set.next()) {
				reservation.setId(set.getInt("id"));
				reservation.setUserId(set.getInt("user_id"));
				reservation.setVehicleId(set.getInt("vehicle_id"));
			}

			// Close the connection
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the model
		return reservation;
	}
	
	/**
	 * Get the reservation information depending on the given id
	 * @param id
	 * @return a single reservation model depending op the id that is passed trough
	 */
	@SuppressWarnings("deprecation")
	public Reservation getById(int id){
		// Declare a new model for returning
		Reservation reservation = new Reservation();
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"SELECT * FROM reservation WHERE id = ?");
			// Declare the values
			statement.setInt(1, id);
			// Declare the resultset
			ResultSet set = statement.executeQuery();
			// Fill the reservation with the values form the resultset
			while (set.next()) {
				reservation.setId(set.getInt("id"));
				reservation.setUserId(set.getInt("user_id"));
				reservation.setVehicleId(set.getInt("vehicle_id"));
				
				reservation.setStartDate(new java.sql.Date(new java.util.Date(set.getString("startdate")).getTime()));
				reservation.setEndDate(new java.sql.Date(new java.util.Date(set.getString("enddate")).getTime()));
			}

			// Close the connection
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return the model
		return reservation;
	}

	/**
	 * Make an arraylist holding al the reservations
	 * @return a list with all the reservation information
	 */
	public ArrayList<Reservation> all() {
		// Make a new list for filling.
		ArrayList<Reservation> allReservations = new ArrayList<Reservation>();

		// Make an empty result set
		ResultSet set = null;

		// Declare the database, and establish the connection
		Database database = new Database();
		database.connect();

		// Try to retrieve information from the database.
		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM reservation");
			
			// Fill the resultset
			set = statement.executeQuery();

			while (set.next()) {
				// Declare a new model
				Reservation reservation = new Reservation();
				
				// Fill the model with all the information
				reservation.setId(set.getInt("id"));
				reservation.setUserId(set.getInt("user_id"));
				reservation.setVehicleId(set.getInt("vehicle_id"));
				
				// Format the date
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
				// Set the dates
				reservation.setStartDate(new java.sql.Date(format.parse(set.getString("startdate")).getTime()));
				reservation.setEndDate(new java.sql.Date(format.parse(set.getString("enddate")).getTime()));
				// Fill additional information
				reservation.setStatus(set.getBoolean("status"));
				reservation.setPickedUp(set.getBoolean("picked_up"));
				
				// Add the reservation to the list
				allReservations.add(reservation);
			}
			// Close the connection
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return the list of reservations.
		return allReservations;
	}
	
	/**
	 * Get the reservations for the given date
	 * @param startdate
	 * @return an arraylist that is filled with the reservation information
	 * for the given date
	 */
	public ArrayList<Reservation> latest(String startdate) {
		// Make a new list for filling.
		ArrayList<Reservation> allReservations = new ArrayList<Reservation>();

		// Make an empty result set
		ResultSet set = null;

		// Declare the database, and establish the connection
		Database database = new Database();
		database.connect();

		// Try to retrieve information from the database.
		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM reservation WHERE startdate = ?");
			// Set variables
			statement.setString(1, startdate);

			set = statement.executeQuery();

			while (set.next()) {
				// Declare a new model
				Reservation reservation = new Reservation();
				
				// Fill the model with all the information
				reservation.setId(set.getInt("id"));
				reservation.setUserId(set.getInt("user_id"));
				reservation.setVehicleId(set.getInt("vehicle_id"));
				// Format the date
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
				
				reservation.setStartDate(new java.sql.Date(format.parse(set.getString("startdate")).getTime()));
				reservation.setEndDate(new java.sql.Date(format.parse(set.getString("enddate")).getTime()));
				// Set additional values
				reservation.setStatus(set.getBoolean("status"));
				reservation.setPickedUp(set.getBoolean("picked_up"));
				
				// Add the reservation to the list
				allReservations.add(reservation);
			}
			// Close the connection
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return the list of reservations.
		return allReservations;
	}
	
	/**
	 * Delete the reservation depending on the vehicleoption id
	 * @param id
	 */
	public void Delete(int id){
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			
			// Create the delete query
			PreparedStatement statement = database.getConnection().prepareStatement("DELETE FROM reservation WHERE id = ?");
			
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
	 * Get the reservationid
	 * @return the id for the reservation
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id for the reservation
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the userid
	 * @return the userid for the reservation
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Set the userid for the reservation
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Get the vehicleid
	 * @return the vehicleid for the reservation
	 */
	public int getVehicleId() {
		return vehicleId;
	}

	/**
	 * Set the vehicleid for the reservation
	 * @param vehicleId
	 */
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * Get the startdate
	 * @return the startdate for the reservation
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Set the startdate for the reservation
	 * @param date
	 */
	public void setStartDate(Date date) {
		this.startDate = date;
	}
	
	/**
	 * Get the enddate
	 * @return the enddate for the reservation
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Set the enddate for the reservation
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Get the status
	 * @return the current status for the reservation
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Set the status of the reservation
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * get the pickup status
	 * @return the status of the pickup for the reservation
	 */
	public boolean isPickedUp() {
		return pickedUp;
	}
	
	/**
	 * Set the pickup status for the reservation
	 * @param pickedUp
	 */
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

}
