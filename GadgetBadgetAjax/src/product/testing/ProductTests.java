package product.testing;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import product.model.Product;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTests {

	private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

	private static final String BASE_URL = "http://localhost:8080/GadgetBadget/WebApi/Products";

	private static final String CONTENT_TYPE = "Content-Type";

	// Test Case to test GET Method
	@Test
	public void test_GET() {
		given().get(BASE_URL).then().statusCode(200).body("ProductList.productId[0]", equalTo(1)).log().all();
	}

	// Test Case to test POST Method
	@Test
	public void test_POST() {
		Product product = new Product();
		product.setProductTitle("Test Product");
		product.setProductDescription("Test Product Description");
		product.setProductType("Selling");
		product.setProductCategory("IT");
		product.setResercherId(2);

		// request body data
		String body = String.format(
				"productTitle=%s&productDescription=%s&" + "productType=%s&productCategory=%s&researcherID=%s",
				"Test Product", "Test Product Description", "Selling", "IT", "2");

		given().with().header(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).accept(ContentType.HTML).body(body)
				.when().post(BASE_URL).then().statusCode(200);

	}

	// Test case to test DELETE method
	@Test
	public void test_DELETE() {

		String body = String.format("productId=%s", "11");
		given().with().header(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).accept(ContentType.JSON).body(body)
				.when().delete(BASE_URL).then().statusCode(200);
	}

	// Test case to test PUT method
	@Test
	public void test_PUT() {
		Product product = new Product();
		product.setProductTitle("Test Product update");
		product.setProductDescription("Test Product Description update");
		product.setProductType("Selling");
		product.setProductCategory("IT");
		product.setResercherId(2);

		String body = String.format(
				"productId=%s&productTitle=%s&productDescription=%s&"
						+ "productType=%s&productCategory=%s&researcherID=%s",
				"8", "Test Product update", "Test Product Description update", "Selling", "IT", "2");

		given().with().header(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).accept(ContentType.HTML).body(body)
				.when().put(BASE_URL).then().statusCode(200);

	}

}
