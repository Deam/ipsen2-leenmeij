package com.leenmeij.app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.leenmeij.app.models.User;
import com.leenmeij.app.views.users.AddUser;
import com.leenmeij.app.views.users.EditUser;
import com.leenmeij.app.views.users.Login;
import com.leenmeij.app.views.users.Overview;

public class UserController implements ActionListener {

	// Create the views.
	public Login login;
	public AddUser addUser;
	public EditUser editUser;
	private Overview userOverview;
	
	public static JTable customerTable;

	// Create new model.
	public User user;

	public UserController() {
		// Initialize views.
		login = new Login();
		addUser = new AddUser();
		editUser = new EditUser();

		// Initialize model.
		user = new User();
	}

	/**
	 * Initialize the login view, and also making it visible.
	 */
	public void initLogin() {
		// Set view visibility true.
		login.setVisible(true);

		// Add action listeners to view.
		login.btnInloggen.addActionListener(this);
		login.btnAnnuleren.addActionListener(this);
	}

	/**
	 * Initialize the add user view and also makes the view visible.
	 */
	public void getAdd() {
		// Add the action listener.
		addUser.addButton.addActionListener(this);

		// Make view visible.
		addUser.setVisible(true);
	}

	/**
	 * Initialize the edit user view, and also makes the view visible.
	 * 
	 * @param id
	 *            The id of the user you want to edit.
	 */
	public void getEdit(int id) {
		// Retrieve information from the database.
		user = user.find(id);

		// Makes view visible, then add the action listener.
		editUser.editButton.addActionListener(this);
		editUser.setVisible(true);

		// Set the text fields within the view.
		editUser.idTextField.setText(Integer.toString(id));
		editUser.emailTextField.setText(user.getEmail());
		editUser.firstNameTextField.setText(user.getFirstName());
		editUser.lastNameTextField.setText(user.getLastName());
		editUser.zipCodeTextField.setText(user.getZipCode());
		editUser.addressLineOneTextField.setText(user.getAddressLineOne());
		editUser.addressLineTwoTextField.setText(user.getAddressLineTwo());
		editUser.cityTextField.setText(user.getCity());
		editUser.countryTextField.setText(user.getCountry());
		editUser.phoneNumberTextField.setText(user.getPhoneNumber());
		editUser.licenseNumberTextField.setText(user.getLicenseNumber());
		editUser.passportNumberTextField.setText(user.getPassportNumber());
		editUser.kvkNumberTextField.setText(user.getKvkNumber());
		editUser.vatNumberTextField.setText(user.getVatNumber());
	}

	public void getOverview() {
		userOverview = new Overview();
		userOverview.tablePanel.setViewportView(getCustomerTable());
		userOverview.editButton.addActionListener(this);
		userOverview.deleteButton.addActionListener(this);
		userOverview.setVisible(true);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {

		/**
		 * ====================================================================
		 * Login view
		 * ====================================================================
		 */
		if (e.getSource() == login.btnInloggen) {
			if (user.checkLogin(login.usernameField.getText(),
					login.passwordField.getText())) {
				// Logged in.
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"Uw e-mail en wachtwoord combinatie is onjuist, probeer het opnieuw.",
								"Fout", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (e.getSource() == login.btnAnnuleren) {
			login.dispose();
		}

		/**
		 * ====================================================================
		 * Add user view
		 * ====================================================================
		 */
		else if (e.getSource() == addUser.addButton) {
			user.setEmail(addUser.emailTextField.getText());
			user.setFirstName(addUser.firstNameTextField.getText());
			user.setLastName(addUser.lastNameTextField.getText());
			user.setAddressLineOne(addUser.addressLineOneTextField.getText());
			user.setAddressLineTwo(addUser.addressLineTwoTextField.getText());
			user.setCity(addUser.cityTextField.getText());
			user.setZipCode(addUser.zipCodeTextField.getText());
			user.setCountry(addUser.countryTextField.getText());
			user.setPhoneNumber(addUser.phoneNumberTextField.getText());
			user.setLicenseNumber(addUser.licenseNumberTextField.getText());
			user.setPassportNumber(addUser.passportNumberTextField.getText());
			user.setKvkNumber(addUser.kvkNumberTextField.getText());
			user.setVatNumber(addUser.vatNumberTextField.getText());

			user.save(user);

			JOptionPane.showMessageDialog(null,
					"Gebruiker succesvol aangemaakt", "Succes",
					JOptionPane.INFORMATION_MESSAGE);

			addUser.dispose();
			
			userOverview.tablePanel.setViewportView(getCustomerTable());
			
			// Update the tables in main
			MainController.update();
		}

		/**
		 * ====================================================================
		 * Edit user view
		 * ====================================================================
		 */
		else if (e.getSource() == editUser.editButton) {
			user = new User();

			user.setId(Integer.parseInt(editUser.idTextField.getText()));
			user.setEmail(editUser.emailTextField.getText());
			user.setFirstName(editUser.firstNameTextField.getText());
			user.setLastName(editUser.lastNameTextField.getText());
			user.setAddressLineOne(editUser.addressLineOneTextField.getText());
			user.setAddressLineTwo(editUser.addressLineTwoTextField.getText());
			user.setCity(editUser.cityTextField.getText());
			user.setZipCode(editUser.zipCodeTextField.getText());
			user.setCountry(editUser.countryTextField.getText());
			user.setPhoneNumber(editUser.phoneNumberTextField.getText());
			user.setLicenseNumber(editUser.licenseNumberTextField.getText());
			user.setPassportNumber(editUser.passportNumberTextField.getText());
			user.setKvkNumber(editUser.kvkNumberTextField.getText());
			user.setVatNumber(editUser.vatNumberTextField.getText());

			user.update(user);

			JOptionPane.showMessageDialog(null, "Gebruiker is bijgewerkt",
					"Succes", JOptionPane.INFORMATION_MESSAGE);

			editUser.dispose();
			
			// Update the tables in main
			MainController.update();
			userOverview.tablePanel.setViewportView(getCustomerTable());
		}

		else if (e.getSource() == userOverview.editButton) {
			try {
				System.err.println(customerTable.getModel()
						.getValueAt(customerTable.getSelectedRow(), 0)
						.toString());
				getEdit(Integer.parseInt(customerTable.getModel()
						.getValueAt(customerTable.getSelectedRow(), 0)
						.toString()));
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, "Selecteer een klant", "Fout", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (e.getSource() == userOverview.deleteButton) {
			// Declare the user
			User user = new User();
			// Remove the user from the database
			user.remove(Integer.parseInt(customerTable.getModel()
					.getValueAt(customerTable.getSelectedRow(), 0).toString()));
			
			userOverview.tablePanel.setViewportView(getCustomerTable());
			
			// Update the tables in main
			MainController.update();
		}
	}
	
	/**
	 * Make the customerTable for making the selection whilst we are making a
	 * reservation.
	 */
	public static JTable getSearchCustomerTable(String name) {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Voornaam");
		columnNames.add("Achternaam");
		columnNames.add("Woonplaats");

		// Set the userdata for the columnNames
		Vector<Vector<String>> userList = new Vector<Vector<String>>();
		User user = new User();
		for (User u : user.search(name)) {
			Vector<String> data = new Vector<>();

			data.add(Integer.toString(u.getId()));
			data.add(u.getFirstName());
			data.add(u.getLastName());
			data.add(u.getCity());

			userList.add(data);
		}

		// Create the new JTable
		customerTable = new JTable(userList, columnNames);
		// Set the selection mode to one row only
		customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return customerTable;
	}

	/**
	 * Make the customerTable for making the selection whilst we are making a
	 * reservation.
	 */
	public static JTable getCustomerTable() {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Voornaam");
		columnNames.add("Achternaam");
		columnNames.add("Woonplaats");

		// Set the userdata for the columnNames
		Vector<Vector<String>> userList = new Vector<Vector<String>>();
		// foreach user in the list, add it to the vector
		User user = new User();
		for (User u : user.all()) {
			// Declare a new vector
			Vector<String> data = new Vector<>();
			// Set the data we want to show
			data.add(Integer.toString(u.getId()));
			data.add(u.getFirstName());
			data.add(u.getLastName());
			data.add(u.getCity());
			// Add the vector to the list
			userList.add(data);
		}

		// Create the new JTable
		customerTable = new JTable(userList, columnNames);
		// Set the selection mode to one row only
		customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return customerTable;
	}
	
	/**
	 * Make the customerTable for making the selection whilst we are making a
	 * reservation.
	 */
	public static JTable getLatestCustomerTable() {
		// Add the columnnames we need
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("#");
		columnNames.add("Voornaam");
		columnNames.add("Achternaam");
		columnNames.add("Woonplaats");

		// Set the userdata for the columnNames
		Vector<Vector<String>> userList = new Vector<Vector<String>>();
		// foreach user in the list, add it to the vector
		User user = new User();
		for (User u : user.latest()) {
			// Declare a new vector
			Vector<String> data = new Vector<>();
			// Set the data we want to show
			data.add(Integer.toString(u.getId()));
			data.add(u.getFirstName());
			data.add(u.getLastName());
			data.add(u.getCity());
			// Add the vector to the list
			userList.add(data);
		}

		// Create the new JTable
		customerTable = new JTable(userList, columnNames);
		// Set the selection mode to one row only
		customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		return customerTable;
	}
}