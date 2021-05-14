package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Fund {
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fund", "root", "");

			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	

	public String readFund() {
		
		
		
		String output = "";
		

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr><th>Fund ID</th>" 
			        + "<th>Funder Name</th>"
			        + "<th>Date </th>"
					+ "<th>Desciption </th>" 
			        +"<th>Amount </th>"
					+"<th>Recipiet_Name </th>"+
			      "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from fund";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String fundID = Integer.toString(rs.getInt("fundID"));
				String fundName = rs.getString("fundName");
				String fundDate = rs.getString("fundDate");
				String description = rs.getString("description");
				String amount = rs.getString("amount");
				String recipiet_Name = rs.getString("recipiet_Name");
				

				// Add into the html table

				output += "<tr><td><input id='hidcustomerIDUpdate' name='hidcustomerIDUpdate' type='hidden' value='"
						+ fundID + "'>" + fundName + "</td>";

				output += "<td>" + fundDate + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + recipiet_Name + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-fundID='"
						+ fundID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			
			output += "</table>";
			
		} catch (Exception e) {
			output = "Error while reading the Appointment.";
			System.err.println(e.getMessage());
		}
		
		

		return output;
		
		
	}
	
	public String insertFund(int fundID , String fundName, String fundDate, String description,String amount , String recipiet_Name) {
		
		
		String output = "";
		
		

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into fund (`fundID`,`fundName`,`fundDate`,`description`,`amount` , `recipiet_Name`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, fundID);
			preparedStmt.setString(2, fundName);
			preparedStmt.setString(3, fundDate);
			preparedStmt.setString(4, description);
			preparedStmt.setString(5, amount);
			preparedStmt.setString(6, recipiet_Name);


			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show successful msg.
			String newfund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newfund + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Fund.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
	

	// Update updateFund
	public String updateFund(String fundID , String fundName, String fundDate, String description,String amount , String recipiet_Name) {
		
		
		String output = "";

		
		
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "update fund SET fundName=?,fundDate=?,description=?,amount=?,recipiet_Name=?, WHERE fundID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundID));
			preparedStmt.setString(2, fundName);
			preparedStmt.setString(3, fundDate);
			preparedStmt.setString(4, description);
			preparedStmt.setString(5, amount);
			preparedStmt.setString(6, recipiet_Name);
			
			
			
			// execute prepared statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show successful msg
			String readFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + readFund + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Fund Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	public String deletefund(String fundID) {
		
		
		String output = "";
		
		

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from fund where fundID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			preparedStmt.setInt(1, Integer.parseInt(fundID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			
			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
			
		} catch (Exception e) {
			
			
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting fund.\"}";
			System.err.println(e.getMessage());

		}

		return output;
		
		
	}
}

