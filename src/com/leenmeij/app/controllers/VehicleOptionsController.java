package com.leenmeij.app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.leenmeij.app.models.VehicleOption;
import com.leenmeij.app.views.vehicleoptions.AddOption;
import com.leenmeij.app.views.vehicleoptions.EditOption;
import com.leenmeij.app.views.vehicleoptions.Overview;

public class VehicleOptionsController implements ActionListener {

	// The views relative to the options
	private AddOption addOption;
	private EditOption editOption;
	private Overview optionOverview;

	private static JTable optionsTable;

	private int editID;
	
	public void getAddOption() {
		addOption = new AddOption();
		addOption.addButton.addActionListener(this);
		addOption.setVisible(true);
	}

	public void getEditOption(int id) {
		VehicleOption option = new VehicleOption();
		option = option.getByID(id);
		
		editID = id;

		editOption = new EditOption();

		editOption.nameField.setText(option.getName());
		editOption.priceField.setText(Double.toString(option.getPrice()));

		editOption.editButton.addActionListener(this);

		editOption.setVisible(true);
	}

	public void getOverview() {
		optionOverview = new Overview();
		optionOverview.tablePanel.setViewportView(getVehicleOptionsList());
		optionOverview.editButton.addActionListener(this);
		optionOverview.deleteButton.addActionListener(this);
		optionOverview.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (addOption != null) {
			if (e.getSource() == addOption.addButton) {
				VehicleOption vehicleOption = new VehicleOption();
				vehicleOption.setName(addOption.nameField.getText());
				vehicleOption.setPrice(Double.parseDouble(addOption.priceField
						.getText()));

				vehicleOption.Insert(vehicleOption);
			}
		} 
		if(optionOverview != null){
			if (e.getSource() == optionOverview.editButton) {
				try {
					getEditOption(Integer.parseInt(optionsTable.getModel()
							.getValueAt(optionsTable.getSelectedRow(), 0)
							.toString()));
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Selecteer een optie",
							"Fout", JOptionPane.ERROR_MESSAGE);
				}
	
			} else if (e.getSource() == optionOverview.deleteButton) {				
				try {
					VehicleOption option = new VehicleOption();
					option.Delete(Integer.parseInt(optionsTable.getModel()
							.getValueAt(optionsTable.getSelectedRow(), 0)
							.toString()));
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Selecteer een optie",
							"Fout", JOptionPane.ERROR_MESSAGE);
				}
				
				optionOverview.tablePanel.setViewportView(getVehicleOptionsList());
			} else if(e.getSource() == editOption.editButton){
				VehicleOption option = new VehicleOption();
				option.setName(editOption.nameField.getText());
				option.setPrice(Double.parseDouble(editOption.priceField.getText()));
				option.Update(option, editID);
				
				editOption.dispose();
				
				optionOverview.tablePanel.setViewportView(getVehicleOptionsList());
			}
		}
	}

	/**
	 * Make the customerTable for making the selection whilst we are making a
	 * reservation.
	 */
	public static JTable getVehicleOptionsList() {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Naam");
		columnNames.add("Prijs");

		// Set the userdata for the columnNames
		Vector<Vector<String>> optionsList = new Vector<Vector<String>>();
		VehicleOption options = new VehicleOption();
		for (VehicleOption option : options.all()) {
			Vector<String> data = new Vector<>();

			data.add(Integer.toString(option.getId()));
			data.add(option.getName());
			
			DecimalFormat format = new DecimalFormat("#.00");
			
			data.add("€ " + format.format(option.getPrice() * 24));

			optionsList.add(data);
		}

		// Create the new JTable
		optionsTable = new JTable(optionsList, columnNames);
		// Set the selection mode to one row only
		optionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return optionsTable;
	}
}
