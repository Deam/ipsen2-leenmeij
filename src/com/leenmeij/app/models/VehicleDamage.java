package com.leenmeij.app.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

/**
 * This class handles database methods for the vehicledamage
 * Here we only insert and return damage per vehicle
 * 
 * @author Deam Kop (s1075228)
 */
public class VehicleDamage {
	
	private int id;
	private int vehicleID;
	private int reservationID;
	private Date insertDate;
	private String damage;

	/**
	 * Insert the damage into the database
	 * @param damage
	 */
	public void Insert(VehicleDamage damage){
		try {
			Database database = new Database();
			database.connect();
			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"INSERT INTO vehicledamage(vehicleid, reservationid, date, damage) VALUES(?, ?, ?, ?)");

			// Set the reservation information
			statement.setInt(1, damage.vehicleID);
			statement.setInt(2, damage.reservationID);
			
			// Format the date so we can insert it in the right format
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
					
			statement.setString(3, format.format(damage.insertDate));
			
			statement.setString(4, damage.damage);


			// Execute the query
			statement.executeUpdate();

			// Close the connection
			database.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Return a list with the damage
	 * @param vehicleid
	 * @return an arraylist with the damage that belongs to the vehicleid that is passed trough
	 */
	public ArrayList<VehicleDamage> all(int vehicleid){
		// Declare a list for returning
		ArrayList<VehicleDamage> damageList = new ArrayList<VehicleDamage>();
		
		try {
			// Connect to the database
			Database database = new Database();
			database.connect();
			// Create the statement
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM vehicledamage WHERE vehicleid = ?");
			// Set the variables we need
			statement.setInt(1, vehicleid);
			// Fill the resultset
			ResultSet set = statement.executeQuery();
			
			// Set the model information from the resultset
			while (set.next()) {
				VehicleDamage damage = new VehicleDamage();
				damage.setId(set.getInt("id"));
				damage.setVehicleID(vehicleid);
				damage.setReservationID(set.getInt("reservationid"));
				
				// Parse the insertdat
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
				damage.setInsertDate(new java.sql.Date(format.parse(set.getString("date")).getTime()));
				
				damage.setDamage(set.getString("damage"));
				
				// Add the model to the list
				damageList.add(damage);
			}
			
			// close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the list
		return damageList;
	}

	/**
	 * Get the vehicleid
	 * @return the id of the vehicle the damage belongs to
	 */
	public int getVehicleID() {
		return vehicleID;
	}

	/**
	 * Set the id of the vehicle we need
	 * @param vehicleID
	 */
	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	/**
	 * Get the reservationid
	 * @return the id of the reservation where the damage was created
	 */
	public int getReservationID() {
		return reservationID;
	}

	/**
	 * Set the id of the reservation the damage was created
	 * @param reservationID
	 */
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	/**
	 * Get the insertdate
	 * @return the date the damage was inserted
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * Set the insertdate of the damage
	 * @param insertDate
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * Set the damagedescription
	 * @return a description of the damage
	 */
	public String getDamage() {
		return damage;
	}

	/**
	 * Set the description of the damage
	 * @param damage
	 */
	public void setDamage(String damage) {
		this.damage = damage;
	}

	/**
	 * Get the id of the damage
	 * @return the id of the damage
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id for the damage
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
