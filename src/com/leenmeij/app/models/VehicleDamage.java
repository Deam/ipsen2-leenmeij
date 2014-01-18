package com.leenmeij.app.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

public class VehicleDamage {
	
	private int id;
	private int vehicleID;
	private int reservationID;
	private Date insertDate;
	private String damage;

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
	
	public ArrayList<VehicleDamage> all(int vehicleid){
		ArrayList<VehicleDamage> damageList = new ArrayList<VehicleDamage>();
		
		try {
			Database database = new Database();
			database.connect();
			
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM vehicledamage WHERE vehicleid = ?");
			
			statement.setInt(1, vehicleid);
			
			ResultSet set = statement.executeQuery();
			
			while (set.next()) {
				VehicleDamage damage = new VehicleDamage();
				damage.setId(set.getInt("id"));
				damage.setVehicleID(vehicleid);
				damage.setReservationID(set.getInt("reservationid"));
				
				// 
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
				damage.setInsertDate(new java.sql.Date(format.parse(set.getString("date")).getTime()));
				
				damage.setDamage(set.getString("damage"));
				
				damageList.add(damage);
			}
			
			// close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return damageList;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
