package com.leenmeij.app.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.leenmeij.app.utils.Database;

/**
 * This class handles all the database features for the vehicle model.
 * 
 * @author Deam
 * 
 */
public class Vehicle {
	private int id;
	private String brand;
	private String model;
	private int milage;
	private String licenseplate;
	private int vehicleUsage;
	private String comment;
	private String color;
	private double hourlyrate;
	private int vehiclecategoryid;
	private int locked;
	private String image;

	/**
	 * Insert a vehicle model in to the database with all the information we are
	 * setting.
	 * 
	 * @param vehicle
	 */
	public void Insert(Vehicle vehicle) {
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the insert query
			PreparedStatement statement = database
					.getConnection()
					.prepareStatement(
							"INSERT INTO vehicles(brand, model, milage, licenseplate, comment, color, hourlyrate, vehiclecategoryid, locked, vehicleusage, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			// Set the vehicle information
			statement.setString(1, vehicle.getBrand());
			statement.setString(2, vehicle.getModel());
			statement.setInt(3, vehicle.getMilage());
			statement.setString(4, vehicle.getLicenseplate());
			statement.setString(5, vehicle.getComment());
			statement.setString(6, vehicle.getColor());
			statement.setDouble(7, vehicle.getHourlyrate());
			statement.setInt(8, vehicle.getVehiclecategoryid());
			statement.setInt(9, 0);
			statement.setInt(10, vehicle.getVehicleUsage());
			statement.setString(11, vehicle.getImage());

			System.out.println(statement);

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update a vehicle depending on it's ID
	 * 
	 * @param vehicle
	 * @param id
	 */
	public void Update(Vehicle vehicle) {
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the update statement
			PreparedStatement statement = database
					.getConnection()
					.prepareStatement(
							"UPDATE vehicles SET brand = ?, model = ?, milage = ?, licenseplate = ?, vehicleusage = ?, comment = ?, hourlyrate = ?, vehiclecategoryid = ?, locked = ?, image = ?, color = ? WHERE id = ?");
			// Set the vehicle information
			statement.setString(1, vehicle.getBrand());
			statement.setString(2, vehicle.getModel());
			statement.setInt(3, vehicle.getMilage());
			statement.setString(4, vehicle.getLicenseplate());
			statement.setInt(5, vehicle.getVehicleUsage());
			statement.setString(6, vehicle.getComment());
			statement.setDouble(7, vehicle.getHourlyrate());
			statement.setInt(8, vehicle.getVehiclecategoryid());
			statement.setInt(9, vehicle.getLocked());
			statement.setString(10, vehicle.getImage());
			statement.setString(11, vehicle.getColor());
			statement.setInt(12, vehicle.getId());

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the vehicle depending on the vehicle id
	 * 
	 * @param id
	 */
	public void Delete(int id) {
		try {
			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Create the delete query
			PreparedStatement statement = database.getConnection()
					.prepareStatement("DELETE FROM vehicles WHERE id = ?");

			// Set the id to be deleted
			statement.setInt(1, id);

			// Execute statement
			statement.executeUpdate();

			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return an ArrayList with all the vehicles
	 * 
	 * @return
	 */
	public ArrayList<Vehicle> all() {
		// Make a new arraylist to hold all the vehicles.
		ArrayList<Vehicle> vehicles = new ArrayList<>();

		try {
			// Make an empty result set
			ResultSet set = null;

			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Create the query
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM vehicles");

			// Fill the resultset with all entries we get
			set = statement.executeQuery();

			// Loop trough all the entries, and create the models
			while (set.next()) {
				// Declare a new model
				Vehicle vehicle = new Vehicle();
				// Set the retreived information to the model
				vehicle.setId(set.getInt("id"));
				vehicle.setBrand(set.getString("brand"));
				vehicle.setModel(set.getString("model"));
				vehicle.setMilage(set.getInt("milage"));
				vehicle.setLicenseplate(set.getString("licenseplate"));
				vehicle.setVehicleUsage(set.getInt("vehicleusage"));
				vehicle.setComment(set.getString("comment"));
				vehicle.setHourlyrate(set.getDouble("hourlyrate"));
				vehicle.setVehiclecategoryid(set.getInt("vehiclecategoryid"));
				vehicle.setLocked(set.getInt("locked"));
				vehicle.setImage(set.getString("image"));

				// Add the model to the arraylist
				vehicles.add(vehicle);
			}
			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return the filled list.
		return vehicles;
	}
	
	/**
	 * Return an ArrayList with all the vehicles
	 * 
	 * @return
	 */
	public ArrayList<Vehicle> latest() {
		// Make a new arraylist to hold all the vehicles.
		ArrayList<Vehicle> vehicles = new ArrayList<>();

		try {
			// Make an empty result set
			ResultSet set = null;

			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Create the query
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM vehicles ORDER BY id DESC LIMIT 15");

			// Fill the resultset with all entries we get
			set = statement.executeQuery();

			// Loop trough all the entries, and create the models
			while (set.next()) {
				// Declare a new model
				Vehicle vehicle = new Vehicle();
				// Set the retreived information to the model
				vehicle.setId(set.getInt("id"));
				vehicle.setBrand(set.getString("brand"));
				vehicle.setModel(set.getString("model"));
				vehicle.setMilage(set.getInt("milage"));
				vehicle.setLicenseplate(set.getString("licenseplate"));
				vehicle.setVehicleUsage(set.getInt("vehicleusage"));
				vehicle.setComment(set.getString("comment"));
				vehicle.setHourlyrate(set.getDouble("hourlyrate"));
				vehicle.setVehiclecategoryid(set.getInt("vehiclecategoryid"));
				vehicle.setLocked(set.getInt("locked"));
				vehicle.setImage(set.getString("image"));

				// Add the model to the arraylist
				vehicles.add(vehicle);
			}
			// Close the connection
			database.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return the filled list.
		return vehicles;
	}

	/**
	 * Gets all the reservations that are linked to the vehicle.
	 * 
	 * @return A list filled with reservations.
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Reservation> reservations(int id) {
		// Make a new list for filling.
		ArrayList<Reservation> allReservations = new ArrayList<Reservation>();

		// Make an empty result set
		ResultSet set = null;

		// Declare the database, and establish the connection
		Database database = new Database();
		database.connect();

		// Try to retrieve information from the database.
		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"SELECT * FROM reservation WHERE vehicle_id = ?");

			statement.setInt(1, id);
			set = statement.executeQuery();

			while (set.next()) {
				// Declare a new model
				Reservation reservation = new Reservation();

				// Fill the model with all the information
				reservation.setId(set.getInt("id"));
				reservation.setUserId(set.getInt("user_id"));
				reservation.setVehicleId(set.getInt("vehicle_id"));

				String tempStartDate = set.getString("startdate");
				String tempEndDate = set.getString("enddate");

				java.util.Date tempStartDateDate = new java.util.Date(
						tempStartDate);
				java.util.Date tempEndDateDate = new java.util.Date(tempEndDate);

				reservation.setStartDate(new Date(tempStartDateDate.getTime()));
				reservation.setEndDate(new Date(tempEndDateDate.getTime()));
				reservation.setPrice(set.getDouble("price"));
				reservation.setStatus(set.getBoolean("status"));
				reservation.setPickedUp(set.getBoolean("picked_up"));

				// Add the reservation to the list
				allReservations.add(reservation);
			}
			// Close the connection
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return the list of reservations.
		return allReservations;
	}

	/**
	 * Return an ArrayList with all the vehicles
	 * 
	 * @return
	 */
	public ArrayList<Vehicle> allAvailable(Date startdate, Date enddate) {
		// Make a new array list to hold all the vehicles that are available.
		ArrayList<Vehicle> availableVehicles = new ArrayList<Vehicle>();

		// Save all vehicles.
		Vehicle vehicleModel = new Vehicle();
		ArrayList<Vehicle> vehiclesList = vehicleModel.all();

		// Loop through all vehicles and check if they are available.
		for (Vehicle vehicle : vehiclesList) {
			// Loop through vehicle reservations.
			ArrayList<Reservation> tempReservationList = vehicle
					.reservations(vehicle.getId());

			if (tempReservationList.size() != 0) {
				for (Reservation reservation : tempReservationList) {
					// Check if dates is available between the two dates.
					if (startdate.compareTo(reservation.getStartDate()) * reservation.getStartDate().compareTo(enddate) >= 0
							|| startdate.compareTo(reservation.getEndDate()) * reservation.getEndDate().compareTo(enddate) >= 0) {
						// Do nothing, because this vehicle is not available.
					}else {
						availableVehicles.add(vehicle);
					}
				}
			}

			else {
				availableVehicles.add(vehicle);
			}
		}

		// Return list with vehicles.
		return availableVehicles;
	}

	/**
	 * Return a single category name by id
	 * 
	 * @param id
	 * @return
	 */
	public String getSingleCategory(int id) {
		String category = "";
		try {
			// Make an empty result set
			ResultSet set = null;

			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Make the select statement
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"SELECT name FROM vehiclecategory WHERE id = ?");

			statement.setInt(1, id);

			set = statement.executeQuery();

			while (set.next()) {
				category = set.getString("name");
			}

			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Close the connection

		return category;
	}

	/**
	 * Get all categories for filling comboboxes
	 * 
	 * @return
	 */
	public ArrayList<String> getAllCategories() {
		// Declare a new list
		ArrayList<String> categoryList = new ArrayList<>();

		try {
			// Make an empty result set
			ResultSet set = null;

			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();

			// Prepare the statement
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM vehiclecategory");

			set = statement.executeQuery();

			while (set.next()) {
				categoryList.add(set.getString("name"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryList;
	}

	/**
	 * Lock the vehicle, so other users can't make a reservation for it.
	 * 
	 * @param id
	 * @param locked
	 */
	public void setVehicleAvailable(int id, boolean locked) {
		// Declare the database, and establish the connection
		Database database = new Database();
		database.connect();

		try {
			// Make the statement
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"UPDATE vehicles SET locked = ? WHERE id = ?");

			// Lock the vehicle for doublereservation purposes
			int isLocked = 0;
			if (locked) {
				isLocked = 1;
			} else {
				isLocked = 0;
			}

			// Fill in the information
			statement.setInt(1, isLocked);
			statement.setInt(2, id);

			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Close the connection
		database.close();
	}

	public boolean checkAvailability(int vehicleID) {
		// Make an empty result set
		ResultSet set = null;

		// Declare the database, and establish the connection
		Database database = new Database();
		database.connect();

		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM vehicles WHERE id = ?");

			statement.setInt(1, vehicleID);

			set = statement.executeQuery();

			while (set.next()) {
				if (set.getInt("locked") == 1) {
					return false;
				} else {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Get a vehicle by id
	 * 
	 * @param id
	 * @return
	 */
	public Vehicle getById(int id) {
		// Declare a new model
		Vehicle vehicle = new Vehicle();

		// Try to make a connection to the database, and fetch a vehicle.
		try {
			// Make an empty result set
			ResultSet set = null;

			// Declare the database, and establish the connection
			Database database = new Database();
			database.connect();
			PreparedStatement statement = database.getConnection()
					.prepareStatement("SELECT * FROM vehicles WHERE id = ?");
			statement.setInt(1, id);

			// Insert statement into resultset
			set = statement.executeQuery();

			// Fill out the customer information
			set.next();
			// Vehicle id is the selected id, so no need to get it from the
			// database
			vehicle.setId(id);
			// Get all the additional information
			vehicle.setBrand(set.getString("brand"));
			vehicle.setModel(set.getString("model"));
			vehicle.setMilage(set.getInt("milage"));
			vehicle.setLicenseplate(set.getString("licenseplate"));
			vehicle.setVehicleUsage(set.getInt("vehicleusage"));
			vehicle.setComment(set.getString("comment"));
			vehicle.setColor(set.getString("color"));
			vehicle.setHourlyrate(set.getDouble("hourlyrate"));
			vehicle.setVehiclecategoryid(set.getInt("vehiclecategoryid"));
			vehicle.setLocked(set.getInt("locked"));
			vehicle.setImage(set.getString("image"));

			// Close the database connection
			database.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return vehicle;
	}

	/**
	 * Searches the database for a vehicle that contains the name that is being
	 * passed trough.
	 * 
	 * @param name
	 * @return A vehicle object.
	 */
	public ArrayList<Vehicle> search(String name) {
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

		// Create new database and result set.
		ResultSet resultSet = null;
		Database database = new Database();

		// Create a connection with the database.
		database.connect();

		// Try to retrieve information from the database.
		try {
			PreparedStatement statement = database.getConnection()
					.prepareStatement(
							"SELECT * FROM vehicles WHERE brand LIKE '%" + name
									+ "%' OR model LIKE '%" + name + "%' ");

			// Execute the query.
			resultSet = statement.executeQuery();

			// Loop through the results and set the user information.
			while (resultSet.next()) {
				// Declare a new model
				Vehicle vehicle = new Vehicle();
				// Set the retreived information to the model
				vehicle.setId(resultSet.getInt("id"));
				vehicle.setBrand(resultSet.getString("brand"));
				vehicle.setModel(resultSet.getString("model"));
				vehicle.setMilage(resultSet.getInt("milage"));
				vehicle.setLicenseplate(resultSet.getString("licenseplate"));
				vehicle.setVehicleUsage(resultSet.getInt("vehicleusage"));
				vehicle.setComment(resultSet.getString("comment"));
				vehicle.setHourlyrate(resultSet.getDouble("hourlyrate"));
				vehicle.setVehiclecategoryid(resultSet
						.getInt("vehiclecategoryid"));
				vehicle.setLocked(resultSet.getInt("locked"));
				vehicle.setImage(resultSet.getString("image"));
				vehicleList.add(vehicle);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Close database connection.
		database.close();

		return vehicleList;
	}

	/**
	 * Getters and setters for the vehicle model
	 * 
	 * @return
	 */
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMilage() {
		return milage;
	}

	public void setMilage(int milage) {
		this.milage = milage;
	}

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getHourlyrate() {
		return hourlyrate;
	}

	public void setHourlyrate(double hourlyrate) {
		this.hourlyrate = hourlyrate;
	}

	public int getVehiclecategoryid() {
		return vehiclecategoryid;
	}

	public void setVehiclecategoryid(int vehiclecategoryid) {
		this.vehiclecategoryid = vehiclecategoryid;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getVehicleUsage() {
		return vehicleUsage;
	}

	public void setVehicleUsage(int vehicleUsage) {
		this.vehicleUsage = vehicleUsage;
	}
}
