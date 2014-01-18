package com.leenmeij.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.leenmeij.app.models.VehicleDamage;

public class VehicleDamageController {
	
	private static JTable damageTable;
	/**
	 * Make the damagetable per vehicle for making the selection whilst we are making a
	 * reservation.
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

			data.add(Integer.toString(v.getId()));
			data.add(Integer.toString(v.getReservationID()));
			
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
			
			data.add(format.format(v.getInsertDate()));
			data.add(v.getDamage());

			vehicleList.add(data);
		}

		// Create the new JTable
		damageTable = new JTable(vehicleList, columnNames);
		// Set the selection mode to one row only
		damageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return damageTable;
	}
}
