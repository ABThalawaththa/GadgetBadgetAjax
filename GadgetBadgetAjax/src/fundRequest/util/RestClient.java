package fundRequest.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class RestClient {
	
	//
	//url to access other services
	
	private static final String REST_URI = "http://localhost:8081/GadgetBadget/WebApi";
	private final Client client;

	public RestClient() {
		super();

		client = Client.create();

	}

	/*
	*get the session from user management service
	*/
	public String getSession() {

		final WebResource webResource = client.resource(REST_URI + "/UserService/sessiondata/");

        final ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);
        
		String test = response.getEntity(String.class);
		return test;
		
	}

	/*
	*get all the products from product management service
	*/
	public String getAllProducs() {
		// TODO Auto-generated method stub
		final WebResource webResource = client.resource(REST_URI + "/Products/funding");

        final ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);
        
		String test = response.getEntity(String.class);
		return test;
	}
	
	
	

}

