package com.leenmeij.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.leenmeij.app.models.VehicleDamage;

/**
 * Here we return a table with the damage per vehicle
 * @author Deam Kop (s1075228)
 */
public class VehicleDamageController {
	
	private static JTable damageTable;
	
	/**
	 * Make the damagetable per vehicle for making the selection whilst we are making a
	 * reservation.
	 * @param vehicleid
	 * @return the damage per vehicle
	 */
	public static JTable getDamageTable(int vehicleid) {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Reservering");
		columnNames.add("Datum");
		columnNames.add("Schade");

		// Set the userdata for the columnNames
		Vector<Vector<String>> vehicleList = new Vector<Vector<String>>();
		VehicleDamage vehicle = new VehicleDamage();
		for (VehicleDamage v : vehicle.all(vehicleid)) {
			Vector<String> data = new Vector<>();
			// Add data to the vector
			data.add(Integer.toString(v.getId()));
			data.add(Integer.toString(v.getReservationID()));
			// Format the date
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
			// Add the date to the datalist
			data.add(format.format(v.getInsertDate()));
			// Add the damage discription
			data.add(v.getDamage());
			// Add the data to the list
			vehicleList.add(data);
		}

		// Create the new JTable
		damageTable = new JTable(vehicleList, columnNames);
		// Set the selection mode to one row only
		damageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Return the table
		return damageTable;
	}
}
