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

public class VehicleController implements ActionListener{

	private Vehicle vehicle;

	private AddVehicle addVehicle;
	private EditVehicle editVehicle;
	private Overview vehicleOverview;
	
	public static JTable vehicleTable;

	public VehicleController() {
		vehicle = new Vehicle();
	}

	public void getAddVehicle() {
		addVehicle = new AddVehicle();

		addVehicle.addButton.addActionListener(this);
		addVehicle.selectImageButton.addActionListener(this);

		addVehicle.setVisible(true);
	}

	public void getEditVehicle(int id) {
		// Create new view, set the actionlisteners and visibility.
		editVehicle = new EditVehicle();

		editVehicle.editButton.addActionListener(this);
		editVehicle.selectImageButton.addActionListener(this);
		
		editVehicle.damagePanels.setViewportView(VehicleDamageController.getDamageTable(id));

		editVehicle.setVisible(true);

		// Get vehicle from the database.
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
	}

	public void getOverview() {
		vehicleOverview = new Overview();
		vehicleOverview.tablePanel.setViewportView(getVehicleTable());
		vehicleOverview.editButton.addActionListener(this);
		vehicleOverview.deleteButton.addActionListener(this);
		vehicleOverview.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * ====================================================================
		 * Add vehicle
		 * ====================================================================
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

					vehicle.Insert(vehicle);
					
					JOptionPane.showMessageDialog(null, "Het voertuig is succesvol toegevoegd.");
					
					// Update the tables in main
					MainController.update();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, controleer de gegevens en probeer het opnieuw.");
				}
			}
		}

		/**
		 * ====================================================================
		 * Edit vehicle
		 * ====================================================================
		 */
		if (editVehicle != null) {
			if (e.getSource() == editVehicle.editButton) {
				try {
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
					} else {
						vehicle.setImage(editVehicle.imageTextField.getText());
					}

					vehicle.Update(vehicle);
					
					vehicleOverview.tablePanel.setViewportView(getVehicleTable());
					
					editVehicle.dispose();
					
					JOptionPane.showMessageDialog(null, "Het voertuig is succesvol aangepast");
					
					// Update the tables in main
					MainController.update();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, controleer de gegevens en probeer het opnieuw.");
				}
			}

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

		if (vehicleOverview != null) {
			if (e.getSource() == vehicleOverview.editButton) {			
				try {
					getEditVehicle(Integer.parseInt(vehicleTable.getModel()
							.getValueAt(vehicleTable.getSelectedRow(), 0)
							.toString()));
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Selecteer een voertuig", "Fout", JOptionPane.ERROR_MESSAGE);
				}
			}

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
	 * @return
	 */
	public static DefaultComboBoxModel<String> categoryItems() {
		Vector<String> comboboxItemsVector = new Vector<String>();

		Vehicle vehicle = new Vehicle();

		for (String category : vehicle.getAllCategories()) {
			comboboxItemsVector.add(category);
		}

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				comboboxItemsVector);

		return model;
	}
	
	/**
	 * Make the vehicleTable for making the selection whilst we are making a
	 * reservation.
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
	 * Make the vehicleTable for making the selection whilst we are making a
	 * reservation.
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
	 * Make the vehicleTable for making the selection whilst we are making a
	 * reservation.
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
	 * Make the customerTable for making the selection whilst we are making a
	 * reservation.
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
