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

/**
 * Here we make the views for the vehicleoptions
 * Add the actionlisteners, and create the table
 * @author Deam
 *
 */
public class VehicleOptionsController implements ActionListener {

	// The views relative to the options
	private AddOption addOption;
	private EditOption editOption;
	private Overview optionOverview;

	private static JTable optionsTable;

	private int editID;
	
	/**
	 * Get the option adding view
	 * Declare the actionlistener
	 */
	public void getAddOption() {
		addOption = new AddOption();
		addOption.addButton.addActionListener(this);
		addOption.setVisible(true);
	}

	/**
	 * Get the option editing view depending on the id
	 * @param id
	 */
	public void getEditOption(int id) {
		// Declare the model
		VehicleOption option = new VehicleOption();
		option = option.getByID(id);
		// Set editID for later user
		editID = id;
		// Declare the view
		editOption = new EditOption();
		// Set the text for the view
		editOption.nameField.setText(option.getName());
		editOption.priceField.setText(Double.toString(option.getPrice()));
		// Add the actionlistener
		editOption.editButton.addActionListener(this);
		// Set the view visible
		editOption.setVisible(true);
	}

	/**
	 * Get the option overview screen
	 * Declare the actionlisteners
	 */
	public void getOverview() {
		optionOverview = new Overview();
		optionOverview.tablePanel.setViewportView(getVehicleOptionsList());
		optionOverview.editButton.addActionListener(this);
		optionOverview.deleteButton.addActionListener(this);
		optionOverview.setVisible(true);
	}

	/**
	 * Actionlisteners depending on the vehicleoptions
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * Adding vehicleoptions actionlistener
		 */
		if (addOption != null) {
			if (e.getSource() == addOption.addButton) {
				try {
					// Declare a new model
					VehicleOption vehicleOption = new VehicleOption();
					// Set the model information
					vehicleOption.setName(addOption.nameField.getText());
					vehicleOption.setPrice(Double.parseDouble(addOption.priceField
							.getText()));
					// Save the vehicleoption
					vehicleOption.Insert(vehicleOption);
					// Show succes message
					JOptionPane.showMessageDialog(null, "Voertuig optie is succesvol toegevoegd.");
					// Close the view
					addOption.dispose();
				} catch (NumberFormatException e1) {
					// Show an error message
					JOptionPane.showMessageDialog(null, "Er is een foutief bedrag ingevoerd. Pas de gegevens aan, en probeer opnieuw");
				}
			}
		} 
		
		/**
		 * Vehicle overview actionlisteners
		 */
		if(optionOverview != null){
			if (e.getSource() == optionOverview.editButton) {
				try {
					// Pass the id for the edit screen
					getEditOption(Integer.parseInt(optionsTable.getModel()
							.getValueAt(optionsTable.getSelectedRow(), 0)
							.toString()));
				} catch (Exception exception) {
					// Show an error message
					JOptionPane.showMessageDialog(null, "Selecteer een optie",
							"Fout", JOptionPane.ERROR_MESSAGE);
				}
	
			} else if (e.getSource() == optionOverview.deleteButton) {				
				try {
					// Declare the model
					VehicleOption option = new VehicleOption();
					// Delete the selected option
					option.Delete(Integer.parseInt(optionsTable.getModel()
							.getValueAt(optionsTable.getSelectedRow(), 0)
							.toString()));
					// Show a succesmessage
					JOptionPane.showMessageDialog(null, "Voertuigoptie is succesvol verwijderd.");
					// Refresh the jtable
					optionOverview.tablePanel.setViewportView(getVehicleOptionsList());
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Selecteer een optie",
							"Fout", JOptionPane.ERROR_MESSAGE);
				}
				
				optionOverview.tablePanel.setViewportView(getVehicleOptionsList());
			} else if(e.getSource() == editOption.editButton){
				try {
					// Declare the model
					VehicleOption option = new VehicleOption();
					// Set the changed values
					option.setName(editOption.nameField.getText());
					option.setPrice(Double.parseDouble(editOption.priceField.getText()));
					// Update the model
					option.Update(option, editID);
					// Show succes message
					JOptionPane.showMessageDialog(null, "Voertuigoptie succesvol aangepast.");
					// Close the view
					editOption.dispose();
					// Refresh the table
					optionOverview.tablePanel.setViewportView(getVehicleOptionsList());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Er is een foutieve waarde ingevuld. Pas de gegevens aan, en probeer het opnieuw");
				}
			}
		}
	}

	/**
	 * Return a table where we can show the vehicleoptions information
	 * @return a vehicloptionlist filled with all the options
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
			// Add the data to the vector
			data.add(Integer.toString(option.getId()));
			data.add(option.getName());
			// Format the double for showing purposes
			DecimalFormat format = new DecimalFormat("#.00");
			data.add("€ " + format.format(option.getPrice() * 24));
			// Add the data to the list
			optionsList.add(data);
		}

		// Create the new JTable
		optionsTable = new JTable(optionsList, columnNames);
		// Set the selection mode to one row only
		optionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Return the table
		return optionsTable;
	}
}
