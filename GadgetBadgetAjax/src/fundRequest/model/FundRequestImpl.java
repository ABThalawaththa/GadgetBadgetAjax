package fundRequest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fundRequest.util.RestClient;

public class FundRequestImpl implements IFundRequestImpl {

	// create a connection to the database
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/productservicedb", "root", "Asiyaamysql1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// read the all fund requests details from the db

	@Override
	public String readFundRequests() {
		// TODO Auto-generated method stub
		
		String id = "kamal";//getSession();
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Fund ID</th><th>Client ID</th>" + "<th>ProductID</th>"
					+ "<th>Contact Name</th>" + "<th>Contact No</th>" + "<th>Contact Mail</th>" + "<th>Message</th>"
					+ "<th>Organization Name</th>" + "<th>Date</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from fundrequests where clientID = '"+id+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String fundID = Integer.toString(rs.getInt("fundID"));
				String clientID = rs.getString("clientID");
				String productID = Integer.toString(rs.getInt("productID"));
				String contactName = rs.getString("contactName");
				String contactNo = rs.getString("contactNo");
				String contactMail = rs.getString("contactMail");
				String message = rs.getString("message");
				String orgName = rs.getString("orgName");
				Date date = rs.getDate("date");
				// Add into the html table
				output += "<tr><td>" + fundID + "</td>";
				output += "<td>" + clientID + "</td>";
				output += "<td>" + productID + "</td>";
				output += "<td>" + contactName + "</td>";
				output += "<td>" + contactNo + "</td>";
				output += "<td>" + contactMail + "</td>";
				output += "<td>" + message + "</td>";
				output += "<td>" + orgName + "</td>";
				output += "<td>" + date + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='fundID' type='hidden' value='" + fundID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the requests.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// read a fund requests and pass it as a object

	@Override
	public FundRequest getFundRequest(int id) {
		// TODO Auto-generated method stub

		try {
			Connection con = connect();
			if (con == null) {
				System.out.println("Error while connecting to the database ");
				return null;
			}

			String query = "select * from fundrequests where fundID =" + id;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			if (rs.next()) {

				FundRequest fr = new FundRequest();

				fr.setFundID(rs.getInt("fundID"));
				fr.setClientID(rs.getString("clientID"));
				fr.setProductID(rs.getInt("productID"));
				fr.setContactName(rs.getString("contactName"));
				fr.setContactNo(rs.getString("contactNo"));
				fr.setContactMail(rs.getString("contactMail"));
				fr.setMessage(rs.getString("message"));
				fr.setOrgName(rs.getString("orgName"));
				fr.setDate(rs.getDate("date"));

				return fr;
			}
			con.close();

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
		return null;
	}

	// create a new fund request and store the details in the db

	@Override
	public String insertRequest(int productID, String contactName, String contactNo, String contactMail,
			String message, String orgName) {

		// TODO Auto-generated method stub
		String id = "kamal";//getSession();
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into fundrequests(clientID, productID, contactName, "
					+ "contactNo, contactMail, message, orgName,date) " + "values (?, ?, ?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, id);
			preparedStmt.setInt(2, productID);
			preparedStmt.setString(3, contactName);
			preparedStmt.setString(4, contactNo);
			preparedStmt.setString(5, contactMail);
			preparedStmt.setString(6, message);
			preparedStmt.setString(7, orgName);
			preparedStmt.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			System.err.println(e.getMessage());
			output = "Error while inserting the request.";

		}
		return output;
	}

	// update a fund request details

	@Override
	public String updateRequest(int fundID, String clientID, int productID, String contactName, String contactNo,
			String contactMail, String message, String orgName) {
		// TODO Auto-generated method stub
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "UPDATE fundrequests SET clientID=?,productID=?,contactName=?,contactNo=?"
					+ ",contactMail=?,message=?,orgName=?,date=?  WHERE fundID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, clientID);
			preparedStmt.setInt(2, productID);
			preparedStmt.setString(3, contactName);
			preparedStmt.setString(4, contactNo);
			preparedStmt.setString(5, contactMail);
			preparedStmt.setString(6, message);
			preparedStmt.setString(7, orgName);
			preparedStmt.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
			preparedStmt.setInt(9, fundID);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the request.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// delete a fund request

	@Override
	public String deleteRequest(int fundID) {
		// TODO Auto-generated method stub
		
		
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "delete from fundrequests where fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, fundID);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the request.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// read all the fund requests and pass them inside a list

	@Override
	public List<FundRequest> getAllRequests() {
		// TODO Auto-generated method stub

		String id = "kamal";//getSession();
		// Prepare the list to store fund requests
		List<FundRequest> list = new ArrayList<>();

		try {
			Connection con = connect();
			if (con == null) {
				System.out.println("Error while connecting to the database");
				return null;
			}

			String query = "select * from fundrequests where clientID = '" + id + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				int fundID = rs.getInt("fundID");
				String clientID = rs.getString("clientID");
				int productID = rs.getInt("productID");
				String contactName = rs.getString("contactName");
				String contactNo = rs.getString("contactNo");
				String contactMail = rs.getString("contactMail");
				String message = rs.getString("message");
				String orgName = rs.getString("orgName");
				Date date = rs.getDate("date");

				// Add into the list
				FundRequest fr = new FundRequest();
				fr.setFundID(fundID);
				fr.setClientID(clientID);
				fr.setProductID(productID);
				fr.setContactName(contactName);
				fr.setContactNo(contactNo);
				fr.setContactMail(contactMail);
				fr.setMessage(message);
				fr.setOrgName(orgName);
				fr.setDate(date);

				list.add(fr);

			}
			con.close();

		} catch (Exception e) {

			System.out.println("Error while reading the requests.");
			return null;

		}
		return list;
	}
	
	//
	//return funding request by product id
	
	@Override
	public List<FundRequest> getRequestByProducId(int id) {
		// TODO Auto-generated method stub

		// Prepare the list to store fund requests
		List<FundRequest> list = new ArrayList<>();

		try {
			Connection con = connect();
			if (con == null) {
				System.out.println("Error while connecting to the database");
				return null;
			}

			String query = "select * from fundrequests where productID = ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, id);

			// execute the statement
			ResultSet rs = preparedStmt.executeQuery();

			// iterate through the rows in the result set
			while (rs.next()) {
				int fundID = rs.getInt("fundID");
				String clientID = rs.getString("clientID");
				int productID = rs.getInt("productID");
				String contactName = rs.getString("contactName");
				String contactNo = rs.getString("contactNo");
				String contactMail = rs.getString("contactMail");
				String message = rs.getString("message");
				String orgName = rs.getString("orgName");
				Date date = rs.getDate("date");

				// Add into the list
				FundRequest fr = new FundRequest();
				fr.setFundID(fundID);
				fr.setClientID(clientID);
				fr.setProductID(productID);
				fr.setContactName(contactName);
				fr.setContactNo(contactNo);
				fr.setContactMail(contactMail);
				fr.setMessage(message);
				fr.setOrgName(orgName);
				fr.setDate(date);

				list.add(fr);

			}
			//close the db connection
			con.close();

		} catch (Exception e) {

			System.out.println("Error while reading the requests.");
			return null;

		}
		return list;
	}

	//
	//get client id from session
	
	public String getSession() {
		// TODO Auto-generated method stub

		RestClient client = new RestClient();
		String data = client.getSession();
		
		// Convert the input string to a JSON object 
		JsonObject jObject = new JsonParser().parse(data).getAsJsonObject();
		String clientID =jObject.get("username").getAsString();

		return clientID;
	}
	
}
