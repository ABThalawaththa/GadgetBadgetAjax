package product.service;



import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

//This class handles the interservice communication 
//product service and other service
public class ProductInterService {
	private static final String REST_URI = "http://localhost:8080/GadgetBadget/WebApi";
	
	private Client client;

	public ProductInterService() {
		super();

		client = Client.create();
	}

	// get all the funding requests for a particular product from fundRequest
	// service
	public String getAllResquestForProduct(int productID) {

		WebResource webResource = client.resource(REST_URI + "/fundRequest/fund/" + productID);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		String returnString = response.getEntity(String.class);
		return returnString;

	}
	
	// get all the orders belonged to a particalur product from order service
	public String getAllOrdersForProduct(int productID) {

		WebResource webResource = client.resource(REST_URI + "/order-service/readOrdersByProject/" + productID);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		String returnString = response.getEntity(String.class);
		return returnString;

	}
	
	// sending orderID and order status to order service to update the order status
	public String sendOrderStatusDetails(int orderID, String orderStatus) {
		Form input = new Form();
		input.add("orderId", String.valueOf(orderID));
		input.add("orderStatus",orderStatus );

		WebResource webResource = client.resource(REST_URI + "/order-service/updateOrderStatus");

		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).accept("application/json").put(ClientResponse.class,input);
		System.out.print("Fuck you");
		String returnString = response.getEntity(String.class);
		System.out.print(returnString);
		return returnString;

	}

}
