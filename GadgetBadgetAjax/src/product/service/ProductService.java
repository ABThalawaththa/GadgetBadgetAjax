package product.service;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import product.model.IProduct;
import product.model.ProductImpl;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;

@Path("/Products")
public class ProductService {

	IProduct iproduct = new ProductImpl();

	ProductInterService productinterservice = new ProductInterService();

	//To get all the products
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProducts() {

		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();

		Gson gson = gb.create();
		return gson.toJson(iproduct.getAllProducts());
	}

	//To get specific product
	@GET
	@Path("/Specific/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSpecificProduct(@PathParam("productId") int productId) {

		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();

		Gson gson = gb.create();
		return gson.toJson(iproduct.getSpecificProduct(productId));
	}

	// To get products according to category
	@GET
	@Path("/{productType}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductByType(@PathParam("productType") String productType) {

		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();

		Gson gson = gb.create();
		return gson.toJson(iproduct.getProductByType(productType));
	}

	// To get funding requests for specific product
	@GET
	@Path("/{productId}/fundingRequests")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFundingRequestsForProducts(@PathParam("productId") int productID) {
		return productinterservice.getAllResquestForProduct(productID);
	}
	
	// To get orders belonged to specific product
	@GET
	@Path("/{productId}/orders")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOrdersForProduct(@PathParam("productId") int productID) {
		return productinterservice.getAllOrdersForProduct(productID);
	}

	// To insert new product
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertProduct(@FormParam("productTitle") String productTitle,
			@FormParam("productDescription") String productDescription, @FormParam("productType") String productType,
			@FormParam("productCategory") String productCategory, @FormParam("researcherID") int researcherID) {
		return iproduct.insertProduct(productTitle, productDescription, productType, productCategory, researcherID);
	}

	// Update product details
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String updateProduct(@FormParam("productId") int productId, @FormParam("productTitle") String productTitle,
			@FormParam("productDescription") String productDescription, @FormParam("productType") String productType,
			@FormParam("productCategory") String productCategory) {
		return iproduct.updateProduct(productId, productTitle, productDescription, productType, productCategory);
	}
	
	// Update order status of a order of a particular product
	@PUT
	@Path("/{productId}/orders/{orderID}/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateOrderStatus(@PathParam("productId") int productID,
			@PathParam("orderID") int orderID,@PathParam("status") String orderStatus) {
		return productinterservice.sendOrderStatusDetails(orderID, orderStatus);
	}

	// Delete product
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(@FormParam("productId") int productId) {
		return iproduct.deleteProduct(productId);
	}

}
