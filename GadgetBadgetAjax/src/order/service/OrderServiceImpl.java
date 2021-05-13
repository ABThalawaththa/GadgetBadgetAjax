package order.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import order.model.Order;
import order.util.DbConnection;


/**
 * Implementing CRUD operations
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 *
 */
public class OrderServiceImpl implements OrderService {
	
	private static Connection con;

	// method to insert a new order
	@Override
	public String createOrder(Order order) {
		
		String output = "";
		
		try {
				
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				return "Failed to connect to the database for inserting!!!";
			}
			 
			// sql query to insert a new record to order table
			String sqlQuery = " insert into it19151120db.order (`order_id`,`order_desc`,`order_date`,`order_status`,`order_pid`,`order_buyerid`) " + " values (?, ?, ?, ?, ?, ?)";
			
			// validate product id  
			//checkProductAvailability(order);
			
			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
			 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, order.getOrderDesc());
			preparedStmt.setDate(3, Date.valueOf(LocalDate.now())); /* get the current system date as order date */
			preparedStmt.setString(4, order.getOrderStatus());
			preparedStmt.setInt(5, order.getProductId());
			preparedStmt.setInt(6, order.getBuyerId());
			 
			// execute the statement
			preparedStmt.execute();
			 
			// database connection is going to be closed at the end of transaction
			con.close();
			 
			output = "Entered record has been successfully inserted!";
			
		} catch (Exception e) {
			 
			 output = "Failed to insert records due to an error!!";
			 System.err.println(e.getMessage());
		}
		
		return output;
	}
		 

	// method to retrieve all the details of the orders
	@Override
	public String getAllOrders() {
		
		String output = "";
		
		try {
						
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for reading!!!"); 
			}
			 
			// Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr><th> Order ID </th>"
			 		+ "<th> Order Description </th>" 
			 		+ "<th> Order Date </th>" 
			 		+ "<th> Order Status </th>" 
			 		+ "<th> Product ID </th>" 
			 		+ "<th>Buyer ID</th></tr>";
	
			 // sql query to retrieve the details of all orders
			 String sqlQuery = "select * from it19151120db.order";
			 
			 Statement stmt = con.createStatement();
			 
			 ResultSet rs = stmt.executeQuery(sqlQuery);
			 
			 // iterate through the rows in the result set
			 while (rs.next()) {
				 
				 Order order = new Order();
				 
				 order.setOrderId(rs.getInt("order_id"));
				 order.setOrderDesc(rs.getString("order_desc"));
				 order.setOrderDate(rs.getDate("order_date"));
				 order.setOrderStatus(rs.getString("order_status"));
				 order.setProductId(rs.getInt("order_pid"));
				 order.setBuyerId(rs.getInt("order_buyerid"));
				 
				 // Add into the html table
				 output += "<tr><td>" + order.getOrderId() + "</td>"; 
				 output += "<td>" + order.getOrderDesc() + "</td>"; 
				 output += "<td>" + order.getOrderDate() + "</td>"; 
				 output += "<td>" + order.getOrderStatus() + "</td>"; 
				 output += "<td>" + order.getProductId() + "</td>";
				 output += "<td>" + order.getBuyerId() + "</td></tr>";
			 }
			 
			 // database connection is going to be closed at the end of transaction
			 con.close();
			 
			 // Complete the html table
			 output += "</table>"; 
			
		} catch (Exception e) {
			output = "Failed to retrieve records due to an error!!"; 
			System.err.println(e.getMessage());
		}
		
		return output; 
	}

	// method to update an order
	@Override
	public String updateOrder(Order order) {
		
		String output = ""; 
		
		try {
						
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for updating!!!"); 
			}
			 
			// sql query to update the order
			String sqlQuery = "UPDATE it19151120db.order SET order_desc = ?, order_date = ?, order_status = ?, order_pid = ?, order_buyerid = ? WHERE order_id = ?"; 
			 
			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
			 
			// binding values
			preparedStmt.setString(1, order.getOrderDesc());
			preparedStmt.setDate(2, Date.valueOf(LocalDate.now())); /* get the current system date as order date */
			preparedStmt.setString(3, order.getOrderStatus());
			preparedStmt.setInt(4, order.getProductId());
			preparedStmt.setInt(5, order.getBuyerId());
			preparedStmt.setInt(6, order.getOrderId()); 
			
			// execute the statement
			preparedStmt.execute();
			
			// database connection is going to be closed at the end of transaction
			con.close(); 
			
			output = "Entered record has been successfully updated!"; 
			
		 } catch (Exception e) { 
			 output = "Failed to update records due to an error!!"; 
			 System.err.println(e.getMessage()); 
		 } 
		
		 return output; 
	}
	
	// method to delete a record
	@Override
	public String deleteOrder(String orderId) {
		
		String output = ""; 
		
		try {
			
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for deleting!!!"); 
			}
		 
			// sql query to delete a selected record 
			String sqlQuery = "delete from it19151120db.order where order_id = ?";
			
			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(orderId));
			
			// execute the statement
			preparedStmt.execute();
			
			// database connection is going to be closed at the end of transaction
			con.close(); 
			
			output = "Selected record has been successfully deleted!"; 
			
		} catch (Exception e) {
			output = "Failed to delete records due to an error!!"; 
			System.err.println(e.getMessage()); 
		} 
		
		return output; 
	}

	// method to get order by id
	@Override
	public Order getOrderById(int orderId) {
		
		Order order = new Order();
		
		try {
			
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for reading an order!!!"); 
			}
			 
			
			// sql query to retrieve the details of specific order by id
			String sqlQuery = "select * from it19151120db.order where order_id =" + orderId;
			 
			Statement stmt = con.createStatement();
			 
			ResultSet rs = stmt.executeQuery(sqlQuery);
			 
			if (rs.next()) {
								 
				order.setOrderId(rs.getInt("order_id"));
				order.setOrderDesc(rs.getString("order_desc"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setProductId(rs.getInt("order_pid"));
				order.setBuyerId(rs.getInt("order_buyerid"));
				 
			}
			 
			// database connection is going to be closed at the end of transaction
			con.close();
			 
			
		} catch (Exception e) {
			System.out.println("Failed to retrieve record due to an error!!");  
			System.err.println(e.getMessage());
		}
		
		return order;
	}

	// method to get order by project id
	@Override
	public List<Order> getOrdersByProjectId(int projectId) {
		List<Order> orderList = new ArrayList<>();
		
		try {
			
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for reading order details!!!"); 
			}
			 
			
			// sql query to retrieve the details of order by project id
			String sqlQuery = "select * from orderstable where order_pid =" + projectId;
			 
			Statement stmt = con.createStatement();
			 
			ResultSet rs = stmt.executeQuery(sqlQuery);
			 
			while (rs.next()) {
				Order order = new Order();
								 
				order.setOrderId(rs.getInt("order_id"));
				order.setOrderDesc(rs.getString("order_desc"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setProductId(rs.getInt("order_pid"));
				order.setBuyerId(rs.getInt("order_buyerid"));
				
				orderList.add(order);
				 
			}
			 
			// database connection is going to be closed at the end of transaction
			con.close();
			 
			
		} catch (Exception e) {
			System.out.println("Failed to retrieve record due to an error!!");  
			System.err.println(e.getMessage());
		}
		
		return orderList;
	}

	// method to update order status
	@Override
	public String updateOrderStatus(int orderId, String status) {
		String output = ""; 
		
		try {
						
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for updating the status!!!"); 
			}
			 
			// sql query to update the order status
			String sqlQuery = "UPDATE orderstable SET order_status = ? WHERE order_id = ?"; 
			 
			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
			 
			// binding values
			preparedStmt.setString(1, status);
			preparedStmt.setInt(2, orderId); 
			
			// execute the statement
			preparedStmt.execute();
			
			// database connection is going to be closed at the end of transaction
			con.close(); 
			
			output = "Order status has been successfully updated!"; 
			
		 } catch (Exception e) { 
			 output = "Failed to update status due to an error!!"; 
			 System.err.println(e.getMessage()); 
		 } 
		
		 return output; 
		
	}


}
