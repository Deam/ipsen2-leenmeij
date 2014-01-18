package com.leenmeij.app.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

public class Reservation {
	
	private int id;
	private int userId;
	private int vehicleId;
	private Date startDate;
	private Date endDate;
	private double price;
	private boolean status;
	private boolean pickedUp;
	
	/**
	 * Insert a vehicle model in to the database with all the information we are
	 * setting.
	 * 
	 * @param vehicle
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
			
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
					
			statement.setString(3, format.format(reservation.startDate));
			statement.setString(4, format.format(reservation.endDate));
			
			// Check if 
			int picked = 0;
			if(pickedUp){
				picked = 1;
			} else{
				picked = 0;
			}
			statement.setInt(5, picked);
			
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
	
	public void InsertReservationOption (int option_id, int reservationID) {
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"INSERT INTO reservation_vehicle_option(reservation_id, vehicle_option_id) VALUES(?, ?)");
			
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
	
	public Reservation get(int userID, int vehicleID){
		Reservation reservation = new Reservation();
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"SELECT * FROM reservation WHERE user_id = ? AND vehicle_id = ?");
			
			statement.setInt(1, userID);
			statement.setInt(2, vehicleID);
			
			ResultSet set = statement.executeQuery();
			
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
		
		return reservation;
	}
	
	public Reservation getById(int id){
		Reservation reservation = new Reservation();
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"SELECT * FROM reservation WHERE id = ?");
			
			statement.setInt(1, id);
			
			ResultSet set = statement.executeQuery();
			
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
		
		return reservation;
	}

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

			set = statement.executeQuery();

			while (set.next()) {
				// Declare a new model
				Reservation reservation = new Reservation();
				
				// Fill the model with all the information
				reservation.setId(set.getInt("id"));
				reservation.setUserId(set.getInt("user_id"));
				reservation.setVehicleId(set.getInt("vehicle_id"));
				
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
				
				reservation.setStartDate(new java.sql.Date(format.parse(set.getString("startdate")).getTime()));
				reservation.setEndDate(new java.sql.Date(format.parse(set.getString("enddate")).getTime()));
				reservation.setPrice(set.getDouble("price"));
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
			
			statement.setString(1, startdate);

			set = statement.executeQuery();

			while (set.next()) {
				// Declare a new model
				Reservation reservation = new Reservation();
				
				// Fill the model with all the information
				reservation.setId(set.getInt("id"));
				reservation.setUserId(set.getInt("user_id"));
				reservation.setVehicleId(set.getInt("vehicle_id"));
				
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
				
				reservation.setStartDate(new java.sql.Date(format.parse(set.getString("startdate")).getTime()));
				reservation.setEndDate(new java.sql.Date(format.parse(set.getString("enddate")).getTime()));
				reservation.setPrice(set.getDouble("price"));
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
	 * Getters and setters
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

}
