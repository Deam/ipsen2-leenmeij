package com.leenmeij.app.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

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
	private Date createdAt;
	private Date updatedAt;

	/**
	 * Private methods ========================================
	 */

	/**
	 * Public methods ========================================
	 */

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
		if (user.password == user.password) {
			// If the two passwords are equal, return true.
			return true;
		}

		// If something went wrong, return false.
		return false;
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
			PreparedStatement statement = database
					.getConnection()
					.prepareStatement(
							"INSERT INTO users (password, email, firstname, lastname, addresslineone, addresslinetwo, city, zipcode, country, phonenumber, licensenumber, passportnumber, kvknumber, vatnumber, business, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			// Give the query the email of the user.
			statement
					.setString(1,
							"$2y$08$oWYIot20GQqskbvcsJN.YeUiYKh3CKrCR4Tllvu7pEU2TKvtRMirC");
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
				user.setCreatedAt(resultSet.getDate("created_at"));
				user.setUpdatedAt(resultSet.getDate("updated_at"));
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
				user.setCreatedAt(resultSet.getDate("created_at"));
				user.setUpdatedAt(resultSet.getDate("updated_at"));
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
				user.setCreatedAt(resultSet.getDate("created_at"));
				user.setUpdatedAt(resultSet.getDate("updated_at"));
				
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
	 * Getters and setters ========================================
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getKvkNumber() {
		return kvkNumber;
	}

	public void setKvkNumber(String kvkNumber) {
		this.kvkNumber = kvkNumber;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public boolean isBusiness() {
		return business;
	}

	public void setBusiness(boolean business) {
		this.business = business;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
