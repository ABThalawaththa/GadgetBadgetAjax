package order.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * use for building a RESTful web service client
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 * 
 */
public class RestClient {
	
	
	//common URI to access services
	
	private static final String REST_URI = "http://localhost:8081/GadgetBadget/WebApi";
	
	private final Client client; /* Client class - for building a RESTful web service client */

	public RestClient() {
		super();

		client = Client.create();

	}



	// get products from product management service
	public String getProducts() {
		final WebResource webResource = client.resource(REST_URI + "/Products/selling");

        final ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        
		String test = response.getEntity(String.class);
		return test;
	}

}
