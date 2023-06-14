package ca.sheridancollege.christya.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.christya.beans.Bike;
import ca.sheridancollege.christya.beans.Manufacturer;
import ca.sheridancollege.christya.beans.User;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	private int error;
	// private BCryptPasswordEncoder passwordEncoder;

	// Method to SELECT ALL bikes using SQL
	public List<Bike> getBikes() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM bike";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Bike>(Bike.class));
	}

	// Method to SELECT ALL manufacturers using SQL
	public List<Manufacturer> getManufacturers() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM manufacturer";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Manufacturer>(Manufacturer.class));
	}

	// Method to SELECT ALL distinct models of bikes using SQL
	public List<Bike> getModels() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT DISTINCT model FROM bike";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Bike>(Bike.class));
	}

	// Method to insert a record of Bike into the Database
	public void insertBike(int manufacturerID, String model, int year, String colour, double price) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("manufacturerID", manufacturerID);
		namedParameters.addValue("model", model);
		namedParameters.addValue("year", year);
		namedParameters.addValue("colour", colour);
		namedParameters.addValue("price", price);
		String query = "INSERT INTO bike(manufacturerID, model, year, colour, price) VALUES(:manufacturerID, :model, :year, :colour, :price)";
		int rowsAffected = jdbc.update(query, namedParameters);

		if (rowsAffected > 0)
			System.out.println("Inserted bike into database.");
	}

	// Method to update a record of Bike into the Database
	// It is assumed that the method will look for 'model' that were selected by the
	// user and
	// the price will be updated accordingly for that 'model'.
	public void updateBike(String model, double price) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("model", model);
		namedParameters.addValue("price", price);
		String query = "UPDATE bike SET price=:price WHERE model=:model";
		int rowsAffected = jdbc.update(query, namedParameters);

		if (rowsAffected > 0)
			System.out.println("Updated bike's price to specific model into database.");
	}

	// Method to delete a record of Bike with particular 'bikeID' into the Database
	public void deleteBike(int bikeID) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("bikeID", bikeID);
		String query = "DELETE FROM bike WHERE bikeID=:bikeID";
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) {
			error = 0;
			System.out.println("Deleted bike in database.");
		} else {
			error = 1;
			getError();
			System.out.println("This bikeID is not found in the database!");
		}
	}

	// Method to prompt Error message if record is not found in database to the user
	// Error will be prompted if value is 1
	public int getError() {
		return error;
	}

	// Find email User in Database
	public User findUserAccount(String email) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where email=:email";
		parameters.addValue("email", email);
		ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<User>(User.class));
		if (users.size() > 0)
			return users.get(0);
		else
			return null;
	}

	//Create a list of roles
	public List<String> getRolesById(Long userId) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select user_role.userId, sec_role.roleName" 
				+ " FROM user_role, sec_role"
				+ " WHERE user_role.roleId=sec_role.roleId" 
				+ " AND userId=:userId";
		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);

		for (Map<String, Object> row : rows) {
			roles.add((String) row.get("roleName"));
		}

		return roles;
	}

}
