package com.leenmeij.app.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

/**
 * This class handles the user methods
 * Including loggin in, inserting, updating etc.
 * @author Deam
 *
 */
public class User {

	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String addressLineOne;
	private String addressLineTwo;
	private String city;
	private String zipCode;
	private String country;
	private String phoneNumber;
	private String licenseNumber;
	private String passportNumber;
	private String kvkNumber;
	private String vatNumber;
	private boolean business;
	private int company;

	/**
	 * Checks if the user is legit.
	 * 
	 * @param email
	 * @param password
	 * @return True if the user is legit, false if the user is not legit.
	 */
	public boolean checkLogin(String email, String password) {
		// Create new user object.
		User user = new User();

		// Find user by email.
		user = user.find(email);

		// Check if the user password belongs to the user e-mail.
		if (!email.isEmpty() && !password.isEmpty()) {
			for (User u : user.all()) {
				if (u.getEmail().equals(email)) {
					return true;
				}
			}
		}
		
		else if (!password.isEmpty()) {
			return false;
		}
		

		// If something went wrong, return false.
		return false;
	}
	
	/**
	 * Get the role of the user that is logging in
	 * @param email
	 * @return the userrole as a string
	 */
	public String getRole(String email) {
		// Declare a new user
		User user = new User();
		// Find the desired user
		user = user.find(email);
		// Declare variables for later use
		int roleId = 0;
		String roleName = "";
		
		// Connect to the database
		Database database = new Database();
		database.connect();
		
		// Try and get the userroleid
		try {
			// Make the statement
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM userroles WHERE user_id = ?");
			// Declare the variables
			statement.setInt(1, user.getId());
			// Fill the resultset
			ResultSet set = statement.executeQuery();
			// Set the information
			while(set.next()) {
				roleId = set.getInt("role_id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Try to get the role depending on the userroleid
		try {
			// Make the statement
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM roles WHERE id = ?");
			// Set the variables
			statement.setInt(1, roleId);
			// Fill the resultset
			ResultSet set = statement.executeQuery();
			// Set the information gotten from the resultset
			while(set.next()) {
				roleName = set.getString("name");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Close the connection 
		database.close();
		
		// Return the rolename
		return roleName;
	}

	/**
	 * Inserts a new user into the database.
	 * 
	 * @param user
	 */
	public void save(User user) {
		// Create new database.
		Database database = new Database();

		// Create a connection with the database.
		database.connect();

		// Try to retrieve information from the database.
		try {
			// Make the statement
			PreparedStatement statement = database
					.getConnection()
					.prepareStatement(
							"INSERT INTO users (password, email, firstname, lastname, addresslineone, addresslinetwo, city, zipcode, country, phonenumber, licensenumber, passportnumber, kvknumber, vatnumber, business, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			// Set a standard password
			statement.setString(1,"$2y$08$oWYIot20GQqskbvcsJN.YeUiYKh3CKrCR4Tllvu7pEU2TKvtRMirC");
			// Set the variables
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getAddressLineOne());
			statement.setString(6, user.getAddressLineTwo());
			statement.setString(7, user.getCity());
			statement.setString(8, user.getZipCode());
			statement.setString(9, user.getCountry());
			statement.setString(10, user.getPhoneNumber());
			statement.setString(11, user.getLicenseNumber());
			statement.setString(12, user.getPassportNumber());
			statement.setString(13, user.getKvkNumber());
			statement.setString(14, user.getVatNumber());
			statement.setInt(15, 0); // Business
			statement.setInt(16, 0); // Company id.

			// Execute the insert.
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Close database connection.
		database.close();
	}

	/**
	 * Updates a user in the database.
	 * 
	 * @param user
	 */
	public void update(User user) {
		// Create new database.
		Database database = new Database();

		// Retrieve password of the user.
		User password = new User();
		password = password.find(1);
		
		// Create a connection with the database.
		database.connect();

		// Try to retrieve information from the database.
		try {
			PreparedStatement statement = database
					.getConnection()
					.prepareStatement(
							"UPDATE users SET password = ?, email = ?, firstname = ?, lastname = ?, addresslineone = ?, addresslinetwo = ?, city = ?, zipcode = ?, country = ?, phonenumber = ?, licensenumber = ?, passportnumber = ?, kvknumber = ?, vatnumber = ?, business = ?, company_id = ? WHERE id = ?");

			// Give the query the email of the user.
			statement.setString(1, password.getPassword());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getAddressLineOne());
			statement.setString(6, user.getAddressLineTwo());
			statement.setString(7, user.getCity());
			statement.setString(8, user.getZipCode());
			statement.setString(9, user.getCountry());
			statement.setString(10, user.getPhoneNumber());
			statement.setString(11, user.getLicenseNumber());
			statement.setString(12, user.getPassportNumber());
			statement.setString(13, user.getKvkNumber());
			statement.setString(14, user.getVatNumber());
			statement.setInt(15, 0); // Business
			statement.setInt(16, 0); // Company id.
			statement.setInt(17, user.getId()); 

			// Execute the insert.
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Close database connection.
		database.close();
	}

	/**
	 * Searches the database for a user on the email.
	 * 
	 * @param email
	 * @return A user object.
	 */
	public User find(String email) {
		// Create new model.
		User user = new User();

		// Create new database and result set.
		ResultSet resultSet = null;
		Database database = new Database();

		// Create a connection with the database.
		database.connect();

		// Try to retrieve information from the database.
		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM users WHERE email = ?");

			// Give the query the email of the user.
			statement.setString(1, email);

			// Execute the query.
			resultSet = statement.executeQuery();

			// Loop through the results and set the user information.
			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setAddressLineOne(resultSet.getString("addresslineone"));
				user.setAddressLineTwo(resultSet.getString("addresslinetwo"));
				user.setCity(resultSet.getString("city"));
				user.setZipCode(resultSet.getString("zipcode"));
				user.setCountry(resultSet.getString("country"));
				user.setPhoneNumber(resultSet.getString("phonenumber"));
				user.setLicenseNumber(resultSet.getString("licensenumber"));
				user.setPassportNumber(resultSet.getString("passportnumber"));
				user.setKvkNumber(resultSet.getString("kvknumber"));
				user.setVatNumber(resultSet.getString("vatnumber"));
				user.setBusiness(resultSet.getBoolean("business"));
				user.setCompany(resultSet.getInt("company_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Close database connection.
		database.close();

		return user;
	}

	/**
	 * Searches the database for a user on the id.
	 * 
	 * @param id
	 * @return A user object.
	 */
	public User find(int id) {
		// Create new model.
		User user = new User();

		// Create new database and result set.
		ResultSet resultSet = null;
		Database database = new Database();

		// Create a connection with the database.
		database.connect();

		// Try to retrieve information from the database.
		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM users WHERE id = ?");

			// Give the query the id of the user.
			statement.setInt(1, id);

			// Execute the query.
			resultSet = statement.executeQuery();

			// Loop through the results and set the user information.
			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setAddressLineOne(resultSet.getString("addresslineone"));
				user.setAddressLineTwo(resultSet.getString("addresslinetwo"));
				user.setCity(resultSet.getString("city"));
				user.setZipCode(resultSet.getString("zipcode"));
				user.setCountry(resultSet.getString("country"));
				user.setPhoneNumber(resultSet.getString("phonenumber"));
				user.setLicenseNumber(resultSet.getString("licensenumber"));
				user.setPassportNumber(resultSet.getString("passportnumber"));
				user.setKvkNumber(resultSet.getString("kvknumber"));
				user.setVatNumber(resultSet.getString("vatnumber"));
				user.setBusiness(resultSet.getBoolean("business"));
				user.setCompany(resultSet.getInt("company_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Close database connection.
		database.close();

		return user;
	}
	
	/**
	 * Searches the database far a user that contains the name that is being passed trough.
	 * 
	 * @param name
	 * @return A user object.
	 */
	public ArrayList<User> search(String name) {
		ArrayList<User> userList = new ArrayList<User>();

		// Create new database and result set.
		ResultSet resultSet = null;
		Database database = new Database();

		// Create a connection with the database.
		database.connect();

		// Try to retrieve information from the database.
		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM users WHERE firstname LIKE '%" + name +"%' OR lastname LIKE '%" + name + "%' ");

			// Execute the query.
			resultSet = statement.executeQuery();

			// Loop through the results and set the user information.
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setAddressLineOne(resultSet.getString("addresslineone"));
				user.setAddressLineTwo(resultSet.getString("addresslinetwo"));
				user.setCity(resultSet.getString("city"));
				user.setZipCode(resultSet.getString("zipcode"));
				user.setCountry(resultSet.getString("country"));
				user.setPhoneNumber(resultSet.getString("phonenumber"));
				user.setLicenseNumber(resultSet.getString("licensenumber"));
				user.setPassportNumber(resultSet.getString("passportnumber"));
				user.setKvkNumber(resultSet.getString("kvknumber"));
				user.setVatNumber(resultSet.getString("vatnumber"));
				user.setBusiness(resultSet.getBoolean("business"));
				user.setCompany(resultSet.getInt("company_id"));
				
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Close database connection.
		database.close();

		return userList;
	}
	
	/**
	 * Returns a list with all the customers
	 * @return
	 */
	public ArrayList<User> all(){
		// Make a new list for filling.
		ArrayList<User> allUsers = new ArrayList<User>();
		
		// Make an empty resultset
		ResultSet set = null;
		
		// Declare the database, and establish the connection
		Database database = new Database();
		database.connect();
		
		// Try to retreive information from the database.
		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM users");
			
			set = statement.executeQuery();
			
			while(set.next()){
				// Declare a new model
				User user = new User();
				// Fill the model with all the information
				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setFirstName(set.getString("firstname"));
				user.setLastName(set.getString("lastname"));
				user.setAddressLineOne(set.getString("addresslineone"));
				user.setAddressLineTwo(set.getString("addresslinetwo"));
				user.setCity(set.getString("city"));
				user.setZipCode(set.getString("zipcode"));
				user.setCountry(set.getString("country"));
				user.setPhoneNumber(set.getString("phonenumber"));
				user.setLicenseNumber(set.getString("licensenumber"));
				user.setPassportNumber(set.getString("passportnumber"));
				// Add the user to the list
				allUsers.add(user);
			}
			// Close the connection
			database.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		// Return the list of users.
		return allUsers;
	}
	
	/**
	 * Returns a list with all the customers
	 * @return
	 */
	public ArrayList<User> latest(){
		// Make a new list for filling.
		ArrayList<User> allUsers = new ArrayList<User>();
		
		// Make an empty resultset
		ResultSet set = null;
		
		// Declare the database, and establish the connection
		Database database = new Database();
		database.connect();
		
		// Try to retreive information from the database.
		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM users ORDER BY id DESC LIMIT 15");
			
			set = statement.executeQuery();
			
			while(set.next()){
				// Declare a new model
				User user = new User();
				// Fill the model with all the information
				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setFirstName(set.getString("firstname"));
				user.setLastName(set.getString("lastname"));
				user.setAddressLineOne(set.getString("addresslineone"));
				user.setAddressLineTwo(set.getString("addresslinetwo"));
				user.setCity(set.getString("city"));
				user.setZipCode(set.getString("zipcode"));
				user.setCountry(set.getString("country"));
				user.setPhoneNumber(set.getString("phonenumber"));
				user.setLicenseNumber(set.getString("licensenumber"));
				user.setPassportNumber(set.getString("passportnumber"));
				// Add the user to the list
				allUsers.add(user);
			}
			// Close the connection
			database.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		// Return the list of users.
		return allUsers;
	}
	
	/**
	 * Delete the user with the selected id
	 * @param id
	 */
	public void remove(int id){
		try {
			// Connect to the database
			Database database = new Database();
			database.connect();
			
			// Make the delete statement
			PreparedStatement statement = database.getConnection().prepareStatement("DELETE FROM users WHERE id = ?");
			
			// Set the id to be deleted
			statement.setInt(1, id);
			
			// Execute statement
			statement.executeUpdate();
			
			// Close the connection
			database.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public String toString() {
		return "TODO: Give a nice overview of all user values.";
	}
	
	/**
	 * Get a vehicle by id
	 * 
	 * @param id
	 * @return
	 */
	public User getById(int id) {
		// Declare a new model
		User user = new User();

		// Try to make a connection to the database, and fetch a vehicle.
		try {
			// Make an empty result set
			ResultSet set = null;

			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM users WHERE id = ?");
			statement.setInt(1, id);

			// Insert statement into resultset
			set = statement.executeQuery();

			// Fill out the customer information
			set.next();

			// Get all the additional information
			user.setId(set.getInt("id"));
			user.setEmail(set.getString("email"));
			user.setFirstName(set.getString("firstname"));
			user.setLastName(set.getString("lastname"));
			user.setAddressLineOne(set.getString("addresslineone"));
			user.setAddressLineTwo(set.getString("addresslinetwo"));
			user.setCity(set.getString("city"));
			user.setZipCode(set.getString("zipcode"));
			user.setCountry(set.getString("country"));
			user.setPhoneNumber(set.getString("phonenumber"));
			user.setLicenseNumber(set.getString("licensenumber"));
			user.setPassportNumber(set.getString("passportnumber"));

			// Close the database connection
			database.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return user;
	}

	/**
	 * Get the user id
	 * @return the id of the user
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id of the user
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the email
	 * @return the email adress of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the emailadress for the user
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the password
	 * @return the user password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the user password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the firstname
	 * @return the firstname for the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the firstname for the user
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the lastname
	 * @return the lastname of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the lastname for the user
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the address
	 * @return the streetname for the user
	 */
	public String getAddressLineOne() {
		return addressLineOne;
	}

	/**
	 * Set the address for the user
	 * @param addressLineOne
	 */
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	/**
	 * get the housenumber
	 * @return the housenumber for the user
	 */
	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	/**
	 * Set the housenumber for the user
	 * @param addressLineTwo
	 */
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}
	
	/**
	 * Get the city 
	 * @return the city where the user lives
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set the city the user lives in
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Get the zipcode
	 * @return the zipcode of the user
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Set the zipcode for the user
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Get the country
	 * @return the country where the user lives
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Set the country where the user lives
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Get the phonenumber
	 * @return the phonenumber for the user
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phonenumber for the user
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Get the driverlicense number
	 * @return the number of the driverslicense
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}

	/**
	 * Set the driverslicense number
	 * @param licenseNumber
	 */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	/**
	 * Get the passportnumber
	 * @return the number of the passport
	 */
	public String getPassportNumber() {
		return passportNumber;
	}

	/**
	 * Set the passportnumber
	 * @param passportNumber
	 */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	/**
	 * Get the kvknumber
	 * @return the kvknumber if the user is a business user
	 */
	public String getKvkNumber() {
		return kvkNumber;
	}

	/**
	 * Set the kvknumber for the user if it is a business user
	 * @param kvkNumber
	 */
	public void setKvkNumber(String kvkNumber) {
		this.kvkNumber = kvkNumber;
	}

	/**
	 * Get the vat number
	 * @return a vat number for a business user
	 */
	public String getVatNumber() {
		return vatNumber;
	}

	/**
	 * Set the vatnumber for a business user
	 * @param vatNumber
	 */
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	/**
	 * Set the user as a companyuser
	 * @return true if the user is part of a company
	 */
	public boolean isBusiness() {
		return business;
	}

	/**
	 * Set true if the user is part of a company
	 * @param business
	 */
	public void setBusiness(boolean business) {
		this.business = business;
	}

	/**
	 * Get the company id
	 * @return the id of the company
	 */
	public int getCompany() {
		return company;
	}

	/**
	 * Set the companyid for the user
	 * @param company
	 */
	public void setCompany(int company) {
		this.company = company;
	}
}
