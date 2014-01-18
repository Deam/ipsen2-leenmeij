package com.leenmeij.app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.leenmeij.app.models.Vehicle;
import com.leenmeij.app.views.vehicle.AddVehicle;
import com.leenmeij.app.views.vehicle.EditVehicle;
import com.leenmeij.app.views.vehicle.Overview;

/**
 * Here we handle showing screens, actionlistners and other events
 * We also make a few tables, and the defaultcombobox for the categories
 * @author Deam Kop (s1075228)
 *
 */
public class VehicleController implements ActionListener{

	private AddVehicle addVehicle;
	private EditVehicle editVehicle;
	private Overview vehicleOverview;
	
	public static JTable vehicleTable;

	/**
	 * Get the add vehicle view
	 * Add the actionlisteners
	 */
	public void getAddVehicle() {
		// Declare the view
		addVehicle = new AddVehicle();
		// Add the actionlisteners
		addVehicle.addButton.addActionListener(this);
		addVehicle.selectImageButton.addActionListener(this);

		addVehicle.setVisible(true);
	}
	
	/**
	 * Get the vehicle edit view depending on the id we pass trough
	 * Add the actionlisteners and the JTable
	 * @param id
	 */
	public void getEditVehicle(int id) {
		// Create new view, set the actionlisteners and visibility.
		editVehicle = new EditVehicle();
		// Add the actionlisteners
		editVehicle.editButton.addActionListener(this);
		editVehicle.selectImageButton.addActionListener(this);
		// Set the damagehistory
		editVehicle.damagePanels.setViewportView(VehicleDamageController.getDamageTable(id));
		// Set the view visible
		editVehicle.setVisible(true);

		// Get vehicle from the database.
		Vehicle vehicle = new Vehicle();
		vehicle = vehicle.getById(id);

		// Set information in view.
		editVehicle.idTextField.setText(Integer.toString(id));
		editVehicle.imageTextField.setText(vehicle.getImage());
		editVehicle.categoryComboBox.setSelectedIndex(vehicle
				.getVehiclecategoryid() - 1);
		editVehicle.brandTextField.setText(vehicle.getBrand());
		editVehicle.modelTextField.setText(vehicle.getModel());
		editVehicle.milageTextField.setText(Integer.toString(vehicle
				.getMilage()));
		editVehicle.licenseTextBox.setText(vehicle.getLicenseplate());
		editVehicle.usageText.setText(Integer.toString(vehicle
				.getVehicleUsage()));
		editVehicle.colorTextField.setText(vehicle.getColor());
		editVehicle.hourlyrateTextBox.setText(Double.toString(vehicle
				.getHourlyrate()));
		editVehicle.commentArea.setText(vehicle.getComment());
		
		// Set the checkbox checked if the vehicle is locked
		if(vehicle.getLocked() == 1){
			editVehicle.availableCheckBox.setSelected(true);
		}
	}

	/**
	 * Get the vehicle overview
	 * Add the JTable and actionlisteners to it
	 */
	public void getOverview() {
		vehicleOverview = new Overview();
		vehicleOverview.tablePanel.setViewportView(getVehicleTable());
		vehicleOverview.editButton.addActionListener(this);
		vehicleOverview.deleteButton.addActionListener(this);
		vehicleOverview.setVisible(true);
	}

	/**
	 * Actionlisteners for vehicle related views
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * Add vehicle actionlisteners
		 */
		if (addVehicle != null) {
			if (e.getSource() == addVehicle.selectImageButton) {

				// Create new file chooser.
				JFileChooser chooser = new JFileChooser();

				// Set a filter for the file chooser.
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG afbeeldingen", "jpg");
				chooser.setFileFilter(filter);

				int returnVal = chooser.showOpenDialog(null);

				// If user pressed ok, set image name.
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					addVehicle.pictureLabel.setText(chooser.getSelectedFile()
							.toString());
					addVehicle.imgName = chooser.getSelectedFile().getName();
				}
			}

			else if (e.getSource() == addVehicle.addButton) {
				try {
					Vehicle vehicle = new Vehicle();
					vehicle.setVehiclecategoryid(addVehicle.categoryComboBox
							.getSelectedIndex() + 1);
					vehicle.setBrand(addVehicle.brandTextField.getText());
					vehicle.setModel(addVehicle.modelTextField.getText());
					vehicle.setMilage(Integer.parseInt(addVehicle.milageTextField
							.getText()));
					vehicle.setLicenseplate(addVehicle.licenseTextBox.getText());
					vehicle.setComment(addVehicle.commentArea.getText());
					vehicle.setColor(addVehicle.colorTextField.getText());
					vehicle.setHourlyrate(Double
							.parseDouble(addVehicle.hourlyrateTextBox.getText()));
					vehicle.setImage(addVehicle.imgName);
					vehicle.setVehicleUsage(Integer.parseInt(addVehicle.usageText
							.getText()));

					if (!addVehicle.pictureLabel.getText().equals(
							"Gekozen afbeelding..")) {
						try {
							BufferedImage bi = ImageIO.read(new File(
									addVehicle.pictureLabel.getText()));
							ImageIO.write(bi, "jpg", new File(
									"C:\\Users\\Deam\\Documents\\GitHub\\ipsen3\\public\\uploaded\\vehicles\\"
											+ addVehicle.imgName));
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					} else {
						vehicle.setImage(addVehicle.imageTextField.getText());
					}

					if(vehicle.checkDuplicates(vehicle)){
						vehicle.Insert(vehicle);
						
						JOptionPane.showMessageDialog(null, "Het voertuig is succesvol toegevoegd.");
						
						// Update the tables in main
						MainController.update();
					} else{
						JOptionPane.showMessageDialog(null, "Er bestaat al een voertuig met het zelfde kenteken");
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, controleer de gegevens en probeer het opnieuw.");
				}
			}
		}

		/**
		 * Edit vehicle
		 */
		if (editVehicle != null) {
			if (e.getSource() == editVehicle.editButton) {
				try {
					// Set the model information
					Vehicle vehicle = new Vehicle();
					vehicle.setId(Integer.parseInt(editVehicle.idTextField
							.getText()));
					vehicle.setVehiclecategoryid(editVehicle.categoryComboBox
							.getSelectedIndex() + 1);
					vehicle.setBrand(editVehicle.brandTextField.getText());
					vehicle.setModel(editVehicle.modelTextField.getText());
					vehicle.setMilage(Integer.parseInt(editVehicle.milageTextField
							.getText()));
					vehicle.setLicenseplate(editVehicle.licenseTextBox.getText());
					vehicle.setComment(editVehicle.commentArea.getText());
					vehicle.setColor(editVehicle.colorTextField.getText());
					vehicle.setHourlyrate(Double
							.parseDouble(editVehicle.hourlyrateTextBox.getText()));
					vehicle.setImage(editVehicle.imgName);
					vehicle.setVehicleUsage(Integer.parseInt(editVehicle.usageText
							.getText()));

					// If the picturelabel is changed, upload the new image
					if (!editVehicle.pictureLabel.getText().equals(
							"Gekozen afbeelding..")) {
						try {
							BufferedImage bi = ImageIO.read(new File(
									editVehicle.pictureLabel.getText()));
							ImageIO.write(bi, "jpg", new File(
									"C:\\Users\\Deam\\Documents\\GitHub\\ipsen3\\public\\uploaded\\vehicles\\"
											+ editVehicle.imgName));
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					// Else the image is unchanged
					} else {
						vehicle.setImage(editVehicle.imageTextField.getText());
					}
					// Update the vehicle
					vehicle.Update(vehicle);
					
					// If the checkbox is nog selected, make the vehicle availble
					if(!editVehicle.availableCheckBox.isSelected()){
						vehicle.setVehicleAvailable(Integer.parseInt(editVehicle.idTextField
							.getText()), false);
					}

					// Dipose the view
					editVehicle.dispose();
					// Show a succesmessage
					JOptionPane.showMessageDialog(null, "Het voertuig is succesvol aangepast");
					
					// Update the tables in main
					MainController.update();
					vehicleOverview.tablePanel.setViewportView(getVehicleTable());
					
				} catch (NumberFormatException e1) {
					// Show an error message
					JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, controleer de gegevens en probeer het opnieuw.");
				}
			}
			// Get the imageselector
			else if (e.getSource() == editVehicle.selectImageButton) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG afbeeldingen", "jpg");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out
							.println("Je hebt het volgende bestand geselecteerd: "
									+ chooser.getSelectedFile().getName());
					editVehicle.pictureLabel.setText(chooser.getSelectedFile()
							.toString());
					editVehicle.imgName = chooser.getSelectedFile().getName();
				}
			}
		}

		/**
		 * Get the vehicle edit view
		 * pass the id of the selected vehicle
		 */
		if (vehicleOverview != null) {
			if (e.getSource() == vehicleOverview.editButton) {			
				try {
					System.out.println(vehicleTable.getModel().getValueAt(vehicleTable.getSelectedRow(), 0));
					getEditVehicle(Integer.parseInt(vehicleTable.getModel()
							.getValueAt(vehicleTable.getSelectedRow(), 0)
							.toString()));
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Selecteer een voertuig", "Fout", JOptionPane.ERROR_MESSAGE);
				}
			}
			// Delete the vehicle
			else if (e.getSource() == vehicleOverview.deleteButton) {
				Vehicle vehicle = new Vehicle();

				vehicle.Delete(Integer.parseInt(vehicleTable.getModel()
						.getValueAt(vehicleTable.getSelectedRow(), 0)
						.toString()));
				
				
				vehicleOverview.tablePanel.setViewportView(getVehicleTable());
			}
		}
	}

	/**
	 * Makes a DefaultComboBoxModel, filled with the vehicle categories.
	 * 
	 * @return A DefaultComboBox with the categories
	 */
	public static DefaultComboBoxModel<String> categoryItems() {
		// Declare the new contentholder
		Vector<String> comboboxItemsVector = new Vector<String>();
		// Declare the model
		Vehicle vehicle = new Vehicle();
		// Foreach string, add to the contentholder
		for (String category : vehicle.getAllCategories()) {
			comboboxItemsVector.add(category);
		}
		// Declare the box model
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				comboboxItemsVector);
		// Return the model
		return model;
	}
	
	/**
	 * Make the vehicleTable for showing the vehicle information
	 */
	public static JTable getVehicleTable() {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Merk");
		columnNames.add("Model");
		columnNames.add("Kenteken");

		// Set the userdata for the columnNames
		Vector<Vector<String>> vehicleList = new Vector<Vector<String>>();
		Vehicle vehicle = new Vehicle();

		
		// Foreach vehicle in the list, add it to the vector
		for (Vehicle v : vehicle.all()) {
			Vector<String> data = new Vector<>();
			
			// Set the data we want to show
			data.add(Integer.toString(v.getId()));
			data.add(v.getBrand());
			data.add(v.getModel());
			data.add(v.getLicenseplate());

			// Add the data to the list
			vehicleList.add(data);
		}

		// Create the new JTable
		vehicleTable = new JTable(vehicleList, columnNames);
		// Set the selection mode to one row only
		vehicleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return vehicleTable;
	}
	
	/**
	 * Make the vehicleTable for showing the last 15 entries
	 */
	public static JTable getLatestVehicleTable() {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Merk");
		columnNames.add("Model");
		columnNames.add("Kenteken");

		// Set the userdata for the columnNames
		Vector<Vector<String>> vehicleList = new Vector<Vector<String>>();
		Vehicle vehicle = new Vehicle();

		
		// Foreach vehicle in the list, add it to the vector
		for (Vehicle v : vehicle.latest()) {
			Vector<String> data = new Vector<>();
			
			// Set the data we want to show
			data.add(Integer.toString(v.getId()));
			data.add(v.getBrand());
			data.add(v.getModel());
			data.add(v.getLicenseplate());

			// Add the data to the list
			vehicleList.add(data);
		}

		// Create the new JTable
		vehicleTable = new JTable(vehicleList, columnNames);
		// Set the selection mode to one row only
		vehicleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return vehicleTable;
	}
	

	/**
	 * Make the vehicleTable for showing the vehicle information, and selecting the desired vehicle
	 * @param startdate
	 * @param enddate
	 * @return a table with the vehicles available within the given dates
	 */
	public static JTable getVehicleDateTable(Date startdate, Date enddate) {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Merk");
		columnNames.add("Model");
		columnNames.add("Kenteken");

		// Set the userdata for the columnNames
		Vector<Vector<String>> vehicleList = new Vector<Vector<String>>();
		Vehicle vehicle = new Vehicle();
		
		// Parse the date
		java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime());
		
		// Foreach vehicle in the list, add it to the vector
		for (Vehicle v : vehicle.allAvailable(sqlStartDate, sqlEndDate)) {
			Vector<String> data = new Vector<>();
			
			// Set the data we want to show
			data.add(Integer.toString(v.getId()));
			data.add(v.getBrand());
			data.add(v.getModel());
			data.add(v.getLicenseplate());

			// Add the data to the list
			vehicleList.add(data);
		}

		// Create the new JTable
		vehicleTable = new JTable(vehicleList, columnNames);
		// Set the selection mode to one row only
		vehicleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return vehicleTable;
	}

	/**
	 * Make the vehicleTable for showing the vehicle information, and selecting the desired vehicle
	 * @param name
	 * @return a table filled with vehicles that contain a part of the search name
	 */
	public static JTable getSearchVehicleTable(String name) {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Merk");
		columnNames.add("Model");
		columnNames.add("Kenteken");

		// Set the userdata for the columnNames
		Vector<Vector<String>> vehicleList = new Vector<Vector<String>>();
		Vehicle vehicle = new Vehicle();
		for (Vehicle v : vehicle.search(name)) {
			Vector<String> data = new Vector<>();

			data.add(Integer.toString(v.getId()));
			data.add(v.getBrand());
			data.add(v.getModel());
			data.add(v.getLicenseplate());

			vehicleList.add(data);
		}

		// Create the new JTable
		vehicleTable = new JTable(vehicleList, columnNames);
		// Set the selection mode to one row only
		vehicleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return vehicleTable;
	}
}
