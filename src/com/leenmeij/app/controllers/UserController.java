package com.leenmeij.app.controllers;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.leenmeij.app.models.User;
import com.leenmeij.app.models.UserRole;
import com.leenmeij.app.views.users.AddUser;
import com.leenmeij.app.views.users.EditUser;
import com.leenmeij.app.views.users.Login;
import com.leenmeij.app.views.users.Overview;

/**
 * Here we handle showing screens, actionlistners and other events
 * We also make a few tables
 * @author Deam Kop (s1075228)
 *
 */
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
		login.loginButton.addActionListener(this);
		login.cancelButton.addActionListener(this);
	}

	/**
	 * Initialize the add user view and also makes the view visible.
	 */
	public void getAdd() {
		addUser = new AddUser();
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
		user = new User();
		// Retrieve information from the database.
		user = user.find(id);

		// Makes view visible, then add the action listener.
		editUser.editButton.addActionListener(this);
		editUser.setVisible(true);
		
		UserRole role = new UserRole();
		editUser.roleBox.setSelectedIndex(role.getById(user.getId()) - 1);

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

	/**
	 * Get the overview for the users
	 */
	public void getOverview() {
		userOverview = new Overview();
		userOverview.tablePanel.setViewportView(getCustomerTable());
		userOverview.editButton.addActionListener(this);
		userOverview.deleteButton.addActionListener(this);
		userOverview.setVisible(true);
	}

	/**
	 * Actionlisteners for the user views
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {

		/**
		 * Login view
		 */
		if (e.getSource() == login.loginButton) {
			if (user.checkLogin(login.emailTextField.getText(), login.passwordField.getText())) {
				// If the user is legit, show the main view
				MainController controller = new MainController();
				// Set the user string
				controller.userEmail = login.emailTextField.getText();
				// SHow the view
				controller.showMainView();

				// Dispose the loginscreen
				login.dispose();
			} else {
				// Show an errormessage
				JOptionPane.showMessageDialog(null,"Uw e-mail en wachtwoord combinatie is onjuist, probeer het opnieuw.","Fout", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if (e.getSource() == login.cancelButton) {
			// Dispose the login view
			login.dispose();
		}

		/**
		 * Add user view
		 */
		else if (e.getSource() == addUser.addButton) {
			try{
				// Try and set the user information to the database
				user = new User();
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
				// Save the user information
				user.save(user);
				
				// Get a new user model for id passing
				User u = user.find(addUser.emailTextField.getText());
				// Declare a new model
				UserRole userRole = new UserRole();
				// Set the values
				userRole.setUserID(u.getId());
				userRole.setRoleID(addUser.rolesBox.getSelectedIndex() + 1);
				// Insert the user
				if(u.checkDuplicates(user)){
					userRole.InsertUser(userRole);
					// Update the tables in main
					MainController.update();
					// Show a succesmessage
					JOptionPane.showMessageDialog(null,
							"Gebruiker succesvol aangemaakt", "Succes",
							JOptionPane.INFORMATION_MESSAGE);
					// Dispose the adduser view
					addUser.dispose();
				} else{
					JOptionPane.showMessageDialog(null, "Een gebruiker met dit email adres bestaat al in de database");
				}
				
			} catch(Exception e1){
				// Show an errormessage
				JOptionPane.showMessageDialog(null, "Er zijn incorrecte gegevens ingevuld, controleer de gegevens en probeer het opnieuw");
			}
		}

		/**
		 * Edit user view
		 */
		else if (e.getSource() == editUser.editButton) {
			// Try to edit the user
			try {
				// Declare a new user
				user = new User();
				// Set the updated user information
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
				// Update the user
				user.update(user);
				// Show a succesmessage
				JOptionPane.showMessageDialog(null, "Gebruiker is bijgewerkt",
						"Succes", JOptionPane.INFORMATION_MESSAGE);
				// Close the edit user screen
				editUser.dispose();
				
				// Update the userrole
				UserRole userRole = new UserRole();
				userRole.setUserID(user.getId());
				userRole.setRoleID(editUser.roleBox.getSelectedIndex() + 1);
				userRole.Update(userRole);
				
				// Update the tables in main
				MainController.update();
				userOverview.tablePanel.setViewportView(getCustomerTable());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Er is een foutief nummer ingevoerd, controller de gegevens en probeer het opnieuw");
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, controller de gegevens en probeer het opnieuw");
			}
		}
		
		/**
		 * User overview actionlisteners
		 */
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

		/**
		 * Delete the selected user in de useroverview
		 */
		else if (e.getSource() == userOverview.deleteButton) {
			
			try {
				// Declare the user
				User user = new User();
				// Set the integer for useridchecking
				int id = (Integer.parseInt(customerTable.getModel().getValueAt(customerTable.getSelectedRow(), 0).toString()));
				// Remove the user from the database
				if(id != 1){
					user.remove(id);
				} else{
					JOptionPane.showMessageDialog(null, "De systeemadministrator kan niet verwijderd worden.");
				}
				// Show a succes message
				JOptionPane.showMessageDialog(null, "De gebruiker is succesvol verwijdert");
				// Update the tables in main
				MainController.update();
				userOverview.tablePanel.setViewportView(getCustomerTable());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Er is een ongeldige numerieke waarde geselecteerd, probeer het opnieuw");
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(null, "Er is een fout opgetreden. Probeer het nogmaals of neem contact op met de systeembeerder");
			}
		}
	}
	
	/**
	 * Make the customerTable when we search for a customer
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
	 * Make the customerTable for showing all the user information
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
	 * Make the customertable for the last 15 added users
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
	
	/**
	 * Makes a DefaultComboBoxModel, filled with the userroles.
	 * 
	 * @return A DefaultComboBox with the userroles
	 */
	public static DefaultComboBoxModel<String> userRoles() {
		// Declare the new contentholder
		Vector<String> comboboxItemsVector = new Vector<String>();
		// Declare the model
		UserRole userRole = new UserRole();
		// Foreach string, add to the contentholder
		for (String role : userRole.getAllRoles()) {
			comboboxItemsVector.add(role);
		}
		// Declare the box model
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				comboboxItemsVector);
		// Return the model
		return model;
	}
}