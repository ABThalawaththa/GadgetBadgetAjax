package fundRequest.service;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.GsonBuilder;

import fundRequest.model.FundRequest;
import fundRequest.model.FundRequestImpl;
import fundRequest.util.RestClient;

@Path("/fundRequest")
public class FundRequestService {

	FundRequestImpl fr = new FundRequestImpl();

	//
	// get all the fund request details as a json file
	//

	@GET
	@Path("/getAllRequests")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllRequests() {

		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		return gb.create().toJson(fr.getAllRequests());

	}

	//
	// display all the fund requests as a html table
	//

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readRequests() {
		return fr.readFundRequests();
	}
	
	//
	// insert fund request as a form and store them inside the db
	//

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertRequest(@FormParam("productID") int productID,
			@FormParam("contactName") String contactName, @FormParam("contactNo") String contactNo,
			@FormParam("contactMail") String contactMail, @FormParam("message") String message,
			@FormParam("orgName") String orgName) throws ParseException {

		String output = fr.insertRequest(productID, contactName, contactNo, contactMail, message, orgName);
		return output;
	}

	//
	// get fund details as a xml data fString and update the db
	//

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRequest(String data) throws ParseException {

		// Convert the input string to an XML document
		Document doc = Jsoup.parse(data, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		int fundID = Integer.parseInt(doc.select("fundID").text());
		String clientID = doc.select("clientID").text();
		int productID = Integer.parseInt(doc.select("productID").text());
		String contactName = doc.select("contactName").text();
		String contactNo = doc.select("contactNo").text();
		String contactMail = doc.select("contactMail").text();
		String message = doc.select("message").text();
		String orgName = doc.select("orgName").text();
		// System.out.println(fundID + clientID+ productID+ contactName+ contactNo+
		// contactMail+
		// message+ orgName);

		/*
		 * // Convert the input string to a JSON object JsonObject jObject = new
		 * JsonParser().parse(data).getAsJsonObject();
		 * 
		 * // Read the values from the JSON object int fundID =
		 * jObject.get("fundID").getAsInt(); int clientID =
		 * jObject.get("clientID").getAsInt(); int productID =
		 * jObject.get("productID").getAsInt(); String contactName =
		 * jObject.get("contactName").getAsString(); String contactNo =
		 * jObject.get("contactNo").getAsString(); String contactMail =
		 * jObject.get("contactMail").getAsString(); String message =
		 * jObject.get("message").getAsString(); String orgName =
		 * jObject.get("orgName").getAsString();
		 */

		String output = fr.updateRequest(fundID, clientID, productID, contactName, contactNo, contactMail, message,
				orgName);
		return output;
	}
	
	//
	// delete the fund request by getting the fund id as a xml data string
	//

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRequest(String data) {
		
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(data, "", Parser.xmlParser());

		// Read the value from the element
		int fundID = Integer.parseInt(doc.select("fundID").text());
		String output = fr.deleteRequest(fundID);
		return output;
	}

	//
	// produces fund request details as a xml data by passing the fund id through
	// url

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML })
	public FundRequest getRequest(@PathParam("id") int id) {
		return fr.getFundRequest(id);
	}
	
	
	//
	//pass the funding requests to product service by product id
	//
	@GET
	@Path("/fund/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRequestByProducId(@PathParam("id") int id) {

		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		return gb.create().toJson(fr.getRequestByProducId(id));
	}
	
	//
	//get all products that available for funding
	//
	@GET
	@Path("/fundingproducts")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProducts() {

		RestClient client = new RestClient();
		
		return client.getAllProducs();
	}
	
	
}
