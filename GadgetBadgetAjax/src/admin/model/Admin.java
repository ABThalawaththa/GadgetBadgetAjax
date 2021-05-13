package admin.model;
//IT19058160
//name : W.M.C.S Bandara
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import user.util.Helper;


public class Admin {//public class name CustomerService which implements ICustomer
	//declaring
	//public static final Logger log = Logger.getLogger(AppProperties.class.getName());
	

	private static Statement stmt = null;
	
	private static PreparedStatement pmt=null;
	
	private static Connection con=null;
	

	
	public static Connection getConnecton() {
		//implement methods
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/assigment", "root", ""); 
			//For testing
			System.out.print("Successfully connected"); 
			
			
		}
		catch(Exception e)
		{
			System.out.println("Database connection is not success!");
			//log.log(Level.SEVERE, e.getMessage());
		}
		return con;
	}
	
	
   
    public boolean insertCustomerCare(String from,String to,String subject ,String message){
    	//method implementation
    	
    	
    	String id = Helper.generateStudentIDs(getStudentId());
    	
		boolean isSuccess=false;
		
		
		//check if there are any errors
		try {
			//database connection
			con = getConnecton();
    		stmt = con.createStatement();
			String sql = "insert into sendmessage values('"+id+"','"+from+"' , '"+to+"','"+subject+"','"+message+"')";
			int rs=stmt.executeUpdate(sql);
			
			if(rs > 0) {//if condition
				isSuccess = true;
				
			}
			else {
				isSuccess = false;
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			//log.log(Level.SEVERE, e.getMessage());
		}
		
		return isSuccess;
		
	}
    
    private ArrayList<String> getStudentId() {

    	ArrayList<String> arrayLst1= new ArrayList<String>();//declearing
    	 
    	try {
    		 con = getConnecton();//Database connection
    	
    		 pmt=con.prepareStatement("select id from sendmessage");
    		 ResultSet rs = pmt.executeQuery();
    		 
    		 while(rs.next())//while loop
    		 {
    			 arrayLst1.add(rs.getString("id"));
    		 }
    	}catch(SQLException e) {
    		//log.log(Level.SEVERE, e.getMessage());
    	}
    	finally 
    	{
    		try 
    		{
    			if(pmt != null)
    			 {
    				 pmt.close();
    			 }
    			 if(con != null)
    			 {
    				 con.close();
    			 }
    		}
    		catch(SQLException e)
    		{
    			//log.log(Level.SEVERE, e.getMessage());
    		}
    	}
    	return arrayLst1;//return value
    }
    	 
    
  
    
  
 
    
	public boolean deletemessage(String id) {
		//method implementation
		int idd = Integer.parseInt(id);//convert string to int
		boolean isSuccess = false;
    	
    	try {
    		con = getConnecton();
    		stmt = con.createStatement();
    		String sql = "delete from receivemessage where id='"+idd+"'";
    		int rs = stmt.executeUpdate(sql);
    		
    		if(rs > 0) {
    			isSuccess = true;
    		} 
    		else {
    			isSuccess = false;
    		}
    		
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		//log.log(Level.SEVERE, e.getMessage());
    	}
 	
    	return isSuccess;
	}
	
	
	
	public String displayreceivemessage() 
	 { 
	 String output = ""; 
	 try
	 { 
		 con = getConnecton();
 		stmt = con.createStatement();
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>#</th><th>Name</th><th>Subject</th>" +
	 "<th>Designation</th>" + 
	 "<th>Email</th>" +
	 "<th>Message</th></tr>"; 
	 
	 String query = "select * from receivemessage"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String name = rs.getString("name"); 
	 String subject = rs.getString("subject"); 
	 String designation = rs.getString("designation"); 
	 String email = rs.getString("email"); 
	 String message = rs.getString("message"); 
	 // Add into the html table
	 output += "<tr><td>" + id + "</td>"; 
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + subject + "</td>"; 
	 output += "<td>" + designation + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + message + "</td></tr>"; 
	 // buttons
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	
	
	
	
	public String displaysendmessage() 
	 { 
	 String output = ""; 
	 try
	 { 
		 con = getConnecton();
		stmt = con.createStatement();
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>id</th><th>from</th><th>to</th>" +
	 "<th>subject</th>" + 
	 "<th>message</th></tr>"; 
	 
	 String query = "select * from sendmessage"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String from = rs.getString("fromm"); 
	 String to = rs.getString("too"); 
	 String subject = rs.getString("subject"); 
	 String message = rs.getString("message"); 

	 // Add into the html table
	 output += "<tr><td>" + id + "</td>"; 
	 output += "<td>" + from + "</td>"; 
	 output += "<td>" + to + "</td>"; 
	 output += "<td>" + subject + "</td>"; 
	 output += "<td>" + message + "</td></tr>"; 
	 // buttons
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
}
