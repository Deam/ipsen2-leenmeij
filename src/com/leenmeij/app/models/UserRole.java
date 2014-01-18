package com.leenmeij.app.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

public class UserRole {
	private int userID;
	private int roleID;
	
	
	/**
	 * Insert the userrole in the database
	 * @param role
	 */
	public void InsertRole(String name){
		try {
			Database database = new Database();
			database.connect();
			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"INSERT INTO roles(name) VALUES(?)");

			// Set the reservation information
			statement.setString(1, name);

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			database.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Assign the userrole to the user in the database
	 * @param role
	 */
	public void InsertUser(UserRole role){
		try {
			Database database = new Database();
			database.connect();
			// Prepare the insert query
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"INSERT INTO userroles(user_id, role_id) VALUES(?, ?)");

			// Set the reservation information
			statement.setInt(1, role.userID);
			statement.setInt(2, role.roleID);

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			database.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Update a userrole depending on the user
	 * 
	 * @param option
	 * @param id
	 */
	public void Update(UserRole role){
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			
			// Prepare the update statement
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"UPDATE userroles SET role_id = ? WHERE user_id = ?");
			// Set the role information
			statement.setInt(1, role.getRoleID());
			statement.setInt(2, role.getUserID());
			
			// Execute the query
			statement.executeUpdate();
			
			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Get the id of the role depending on the given userid
	 * @param userID
	 * @return the id of the userrole
	 */
	public Integer getById(int userID){
		// Declare the model
		Integer userRole = 0;

		// Try to make a connection to the database, and fetch a vehicle.
		try {
			// Make an empty result set
			ResultSet set = null;

			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM userroles WHERE user_id = ?");
			// Declare the variables
			statement.setInt(1, userID);

			// Insert statement into resultset
			set = statement.executeQuery();

			// Fill out the customer information
			set.next();
			// Vehicle id is the selected id, so no need to get it from the
			// database
			userRole = set.getInt("role_id");

			// Close the database connection
			database.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return userRole;
	}
	
	/**
	 * Get all userroles for filling comboboxes
	 * 
	 * @return an arraylist filled with all the userroles
	 */
	public ArrayList<String> getAllRoles() {
		// Declare a new list
		ArrayList<String> roleList = new ArrayList<>();

		try {
			// Make an empty result set
			ResultSet set = null;

			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the statement
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM roles");

			// Fill the set
			set = statement.executeQuery();

			// Add the roles to the list
			while (set.next()) {
				roleList.add(set.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleList;
	}
	
	/**
	 * Get the name of the role, depending on the id
	 * @param id
	 * @return the name of the role
	 */
	public String getRoleName(int id){
		// Declare a new list
		String roleName = "";

		try {
			// Make an empty result set
			ResultSet set = null;

			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the statement
			PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM roles WHERE id = ?");

			statement.setInt(1, id);
			
			set = statement.executeQuery();

			while (set.next()) {
				roleName = set.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleName;
	}
	
	/**
	 * Get the userid
	 * @return the userid to get the correspongin role
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Set the userid so we can handle it for searching
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Get the roleid
	 * @return the roleid so we can get the rolename
	 */
	public int getRoleID() {
		return roleID;
	}

	/**
	 * 
	 * @param roleID
	 */
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
}
