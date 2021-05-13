package admin.service;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON


import admin.model.Admin;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/AdminService") 
public class Admin_Service 
{ 
	
 Admin service = new Admin();
 


@GET
@Path("/displayreceivemessage/") 
@Produces(MediaType.TEXT_HTML) 
public String displayreceivemessage() 
 { 
 return service.displayreceivemessage(); 
 }

@GET
@Path("/displaysendmessage/") 
@Produces(MediaType.TEXT_HTML) 
public String displaysendmessage() 
 { 
 return service.displaysendmessage(); 
 }



@POST
@Path("/reply/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertItem(@FormParam("from") String from, 
 @FormParam("to") String to, 
 @FormParam("subject") String subject, 
 @FormParam("message") String message) 
{ 
	String display="none";
 boolean output = service.insertCustomerCare(from,to,subject ,message); 
 if(output == true) {
	 display="Inserted successfully"; 
 }
 else {
	 display="Error when inserting data";
 }
 return display;
}


@DELETE
@Path("/deletemsg/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteItem(String itemData) 
{ 
	String display="no value";
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
 
//Read the value from the element <itemID>
 String id = doc.select("id").text(); 
 boolean output = service.deletemessage(id); 
 if(output == true) {
	 display = "Deleted Sucessfully";
 }
 else {
	 display="not deleted successfully";
 }
return display; 
}



}
