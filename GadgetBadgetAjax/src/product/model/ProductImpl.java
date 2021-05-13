package product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductImpl implements IProduct {
	// Initialize logger
	public static final Logger log = Logger.getLogger(ProductImpl.class.getName());

	private static final String RESEARCHER_ID = "researcherId";

	private static final String PRODUCT_CATEGORY = "productCategory";

	private static final String PRODUCT_TYPE = "productType";

	private static final String PRODUCT_DESCRIPTION = "productDescription";

	private static final String PRODUCT_TITLE = "productTitle";

	private static final String PRODUCT_ID = "productId";

	private static final String PRODUCT_RETURNED = "ProductReturned";

	private static final String DB_CONNECTION_ERROR = "Error while connecting to the database";

	private static final String CONNECTION_ERROR = "ConnectionError";

	private static Connection connection = null;

	private static PreparedStatement preparedStmt = null;

	private static ResultSet rs = null;

	private static Statement statement = null;

	// DB connection method
	public Connection productDBConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/productservicedb", "root",
						"Asiyaamysql1");
			}
			log.log(Level.INFO, "Successfully connected");

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		return connection;
	}

	// Method to insert product
	public String insertProduct(String productTitle, String productDescription, String productType,
			String productCategory, int researcherId) {
		String output = "";
		try {
			connection = productDBConnection();
			if (connection == null) {
				return DB_CONNECTION_ERROR;
			}

			// create a prepared statement
			String query = " insert into products "
					+ "(`productTitle`,`productDescription`,`productType`,`productCategory`,`researcherId`)"
					+ " values (?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, productTitle);
			preparedStmt.setString(2, productDescription);
			preparedStmt.setString(3, productType);
			preparedStmt.setString(4, productCategory);
			preparedStmt.setInt(5, researcherId);
			// execute the statement
			preparedStmt.execute();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return output;
	}

	// Method to read all the products in the database

	public String readAllProducts() {
		String output = "";
		try {
			Connection con = productDBConnection();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Product Title</th>"
					+ "<th>Product Type</th><th>Product Description</th>" + "<th>Product Category</th>"
					+ "<th>Researcher ID</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from products";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String productID = Integer.toString(rs.getInt(PRODUCT_ID));
				String productTitle = rs.getString(PRODUCT_TITLE);
				String productType = rs.getString(PRODUCT_TYPE);
				String productDescription = rs.getString(PRODUCT_DESCRIPTION);
				String productCategory = rs.getString(PRODUCT_CATEGORY);
				String researcherID = Integer.toString(rs.getInt(RESEARCHER_ID));
				// Add into the html table
				output += "<tr><td>" + productTitle + "</td>";
				output += "<td>" + productType + "</td>";
				output += "<td>" + productDescription + "</td>";
				output += "<td>" + productCategory + "</td>";
				output += "<td>" + researcherID + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-productid='" + productID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-productid='" + productID + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Update details of specific product in the database
	public String updateProduct(String productId, String productTitle, String productDescription, String productType,
			String productCategory) {
		String output = "";
		try {
			Connection con = productDBConnection();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE products SET productTitle=?,productDescription=?,productType=?,productCategory=? WHERE productId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, productTitle);
			preparedStmt.setString(2, productDescription);
			preparedStmt.setString(3, productType);
			preparedStmt.setString(4, productCategory);
			preparedStmt.setInt(5, Integer.parseInt(productId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProducts = readAllProducts();
			output = "{\"status\":\"success\", \"data\": \"" + newProducts + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Read all the products belonged to specific type
	public Map<String, Object> getProductByType(String productType) {
		// To return product List
		List<Product> productList = new ArrayList<>();

		// Create Error Message
		Error em = new Error();

		// Initialize Data to send
		Map<String, Object> data = new HashMap<>();
		try {
			connection = productDBConnection();
			if (connection == null) {
				em.setErrorMessage(DB_CONNECTION_ERROR);
				// Return connection error
				data.put(CONNECTION_ERROR, em);
				return data;
			}

			// create a prepared statement
			String query = "select * from products where productType = ?";
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, productType);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt(PRODUCT_ID));
				product.setProductTitle(rs.getString(PRODUCT_TITLE));
				product.setProductDescription(rs.getString(PRODUCT_DESCRIPTION));
				product.setProductType(rs.getString(PRODUCT_TYPE));
				product.setProductCategory(rs.getString(PRODUCT_CATEGORY));
				productList.add(product);
			}
			// return product list
			data.put("ProductList", productList);
			return data;

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
			// return db read error
			data.put("DB Read Error", e.getMessage());
			return data;
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	// Read all the products belonged to specific type
	public Map<String, Object> getProductsOfResearcher(int researcherId) {
		// To return product List
		List<Product> productList = new ArrayList<>();

		// Create Error Message
		Error em = new Error();

		// Initialize Data to send
		Map<String, Object> data = new HashMap<>();
		try {
			connection = productDBConnection();
			if (connection == null) {
				em.setErrorMessage(DB_CONNECTION_ERROR);
				// Return connection error
				data.put(CONNECTION_ERROR, em);
				return data;
			}

			// create a prepared statement
			String query = "select * from products where researcherId = ?";
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, researcherId);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt(PRODUCT_ID));
				product.setProductTitle(rs.getString(PRODUCT_TITLE));
				product.setProductDescription(rs.getString(PRODUCT_DESCRIPTION));
				product.setProductType(rs.getString(PRODUCT_TYPE));
				product.setProductCategory(rs.getString(PRODUCT_CATEGORY));
				productList.add(product);
			}
			// return product list
			data.put("ProductList", productList);
			return data;

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
			// return db read error
			data.put("DB Read Error", e.getMessage());
			return data;
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	// delete a product
	public String deleteProduct(String productID) {
		String output = "";
		try {
			Connection con = productDBConnection();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from products where productId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(productID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProducts = readAllProducts();
			output = "{\"status\":\"success\", \"data\": \"" + newProducts + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the Product.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// get details of specific product
	@Override
	public Map<String, Object> getSpecificProduct(int productId) {
		// Create Error Message
		Error em = new Error();

		// Initialize Data to send
		Map<String, Object> data = new HashMap<>();
		try {
			connection = productDBConnection();
			if (connection == null) {
				em.setErrorMessage(DB_CONNECTION_ERROR);
				data.put(CONNECTION_ERROR, em);
				return data;
			}

			String query = "select * from products where productId =" + productId;
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			// iterate through the rows in the result set
			if (rs.next()) {

				Product product = new Product();
				product.setProductId(rs.getInt(PRODUCT_ID));
				product.setProductTitle(rs.getString(PRODUCT_TITLE));
				product.setProductDescription(rs.getString(PRODUCT_DESCRIPTION));
				product.setProductType(rs.getString(PRODUCT_TYPE));
				product.setProductCategory(rs.getString(PRODUCT_CATEGORY));

				data.put(PRODUCT_RETURNED, product);
			}
			return data;

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
			em.setErrorMessage(e.getMessage());
			data.put("DBReadError", em);
			return data;
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

}
