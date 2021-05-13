package fundRequest.model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class Test {

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String get() {
		return "Hello World";
	}
}
