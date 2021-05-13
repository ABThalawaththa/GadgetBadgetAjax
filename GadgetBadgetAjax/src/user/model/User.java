package user.model;
//IT19058160
//name : W.M.C.S Bandara
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;




public class User {//public class name CustomerService which implements ICustomer
	//declaring
	//public static final Logger log = Logger.getLogger(AppProperties.class.getName());
	private static boolean isSuccess;

	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	
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
	
	
    public boolean validate(String username,String password) {
    	try {
    		con=getConnecton();//database connection
    		stmt=con.createStatement();
    		String sql = "select * from customer where username='"+username+"' and password='"+password+"'";
    		rs=stmt.executeQuery(sql);
    		if(rs.next())//if condition
    		{
    			isSuccess=true;
    		}
    		else {
    			isSuccess=false;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		//log.log(Level.SEVERE, e.getMessage());
    	}
    	return isSuccess;//return values
    }
   
    
  
    
    public boolean insertcustomer(String name, String email, String phone, String username, String password,String type) {
    	//method implementation
    	boolean isSuccess = false;
    	
    	try {
    		//database connection
    		con = getConnecton();
    		stmt = con.createStatement();
    	    String sql = "insert into customer values (0,'"+name+"','"+email+"','"+phone+"','"+username+"','"+password+"','"+type+"')";
    		int rs = stmt.executeUpdate(sql);
    		
    		if(rs > 0) {//if condition
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
    
    public boolean updatecustomer(String id, String name, String email, String phone, String username, String password,String type) {
    	//method implemenation
    	try {
    		//database connection.
    		con = getConnecton();
    		stmt = con.createStatement();
    		String sql = "update customer set name='"+name+"',email='"+email+"',phone='"+phone+"',username='"+username+"',password='"+password+"',type='"+type+"'"
    				+ "where id='"+id+"'";
    		//test
    		int rs = stmt.executeUpdate(sql);
    		
    		if(rs > 0) {
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
 
    

	
	
	
	public String profile(String username) 
	 { 
	 String output = ""; 
	 try
	 { 
		 con = getConnecton();
		stmt = con.createStatement();
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 
	 
	 String query = "select * from customer  where username='"+username+"'"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String name = rs.getString("name"); 
	 String email = rs.getString("email");  
	 String phone = rs.getString("phone"); 
	 String un = rs.getString("username"); 
	 String password = rs.getString("password");
	 String type = rs.getString("type");
	 // Add into the table
	 output += "<p>" +"Id : "+id + "</p>"; 
	 output += "<p>" + "Name : "+name + "</p>"; 
	 output += "<p>" +"Email : "+ email + "</p>"; 
	 output += "<p>" + "Phone : "+phone + "</p>"; 
	 output += "<p>" +"User Name : "+ un + "</p>"; 
	 output += "<p>" +"Password : "+ password + "</p>"; 
	 output += "<p>" +"Type : "+ type + "</p>"; 
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
	
	public void session(String username){
		

		 try
		 { 
			 con = getConnecton();
			stmt = con.createStatement();
		 if (con == null) 
		 { } 
		 // Prepare the html table to be displayed
		 
		 
		 String query = "select * from customer  where username='"+username+"'"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String id = Integer.toString(rs.getInt("id")); 
		 String name = rs.getString("name"); 
		 String email = rs.getString("email");  
		 String phone = rs.getString("phone"); 
		 String un = rs.getString("username"); 
		 String password = rs.getString("password");
		 String type = rs.getString("type");
		 
		 
		 insertsession(id,name,email,phone,un,password,type);
		 }
 	}
 	catch (Exception e) {
 		e.printStackTrace();
 		//log.log(Level.SEVERE, e.getMessage());
 	}
	
	
		 }
	public void insertsession(String id,String name,String email,String phone,String un,String password,String type) {
		
		 try {
	    		//database connection
	    		con = getConnecton();
	    		stmt = con.createStatement();
	    		 String sql = "insert into session values (0,'"+name+"','"+email+"','"+phone+"','"+un+"','"+password+"','"+type+"')";
	    		int rs = stmt.executeUpdate(sql);
	    		
	    		if(rs > 0) {//if condition
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
	}
	public String sessiondata()
	 { 
		 String output = ""; 
		 try
		 { 
			 con = getConnecton();
			stmt = con.createStatement();
		 if (con == null) 
		 { } 
		 // Prepare the html table to be displayed
		 
		 
		 String query = "select * from session"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String id = Integer.toString(rs.getInt("id")); 
		 String name = rs.getString("name"); 
		 String email = rs.getString("email");  
		 String phone = rs.getString("phone"); 
		 String username = rs.getString("username"); 
		 String password = rs.getString("password");
		 String type = rs.getString("type");
		
		 output += "Id : "+id+","; 
		 output +=  "Name : "+name+","; 
		 output +=  "Email : "+ email+"," ; 
		 output +=  "Phone : "+phone+","; 
		 output += "User Name : "+ username+"," ; 
		 output +="Password : "+ password+","; 
		 output += "Type : "+ type;
		 
		 } 
		 con.close(); 
		 // Complete the html table
		
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
	
}
