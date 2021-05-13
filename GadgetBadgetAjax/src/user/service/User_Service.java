package user.service;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import user.model.User;

//For XML

@Path("/UserService") 
public class User_Service 
{ 
	
 User service = new User();
 
@GET
@Path("login/{username}/{password}") 
@Produces(MediaType.TEXT_HTML) 
public String readItems(@PathParam("username") String username, 
		 @PathParam("password") String password) 
 { 
	String display="invalid";
 Boolean output =  service.validate(username, password);
 if(output == true) {
	 service.session(username);
	 display = "valid user";
	 
 }
 else {
	 display="invalid user";
 }
 return display;
 } 
@GET
@Path("/sessiondata/") 
@Produces(MediaType.APPLICATION_JSON) 
public String sessiondata() 
 { 
GsonBuilder gb = new GsonBuilder();
gb.setPrettyPrinting();
 return gb.create().toJson(service.sessiondata()); 
 }



@GET
@Path("/profile/{username}") 
@Produces(MediaType.TEXT_HTML) 
public String readItems3(@PathParam("username") String username) 
 { 
 return service.profile(username); 
 }




@POST
@Path("/register/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertItem2(
 @FormParam("name") String name, 
 @FormParam("email") String email, 
 @FormParam("phone") String phone,
 @FormParam("username") String username,
 @FormParam("password") String password,
 @FormParam("type") String type) 
{ 
	String display="none";
 boolean output = service.insertcustomer(name,email,phone ,username,password,type); 
 if(output == true) {
	 display="Registered successfully"; 
 }
 else {
	 display = "not registered successfully";
 }
 return display;
}




@PUT
@Path("/editprofile/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateItem(String itemData) 
{ 
	String display="none";
//Convert the input string to a JSON object 
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
//Read the values from the JSON object
 String id = itemObject.get("id").getAsString(); 
 String name = itemObject.get("name").getAsString(); 
 String email = itemObject.get("email").getAsString(); 
 String phone = itemObject.get("phone").getAsString(); 
 String username = itemObject.get("username").getAsString(); 
 String password = itemObject.get("password").getAsString(); 
 String type = itemObject.get("type").getAsString();
 Boolean output = service.updatecustomer(id,name, email, phone,username, password,type);
 if(output == true) {
	 display = "updated successfully";
 }
 else {
	 display = "not updated successfully";
 }
return display; 
}




}
