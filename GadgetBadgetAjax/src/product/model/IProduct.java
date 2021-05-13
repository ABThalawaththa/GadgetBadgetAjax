package product.model;

import java.sql.Connection;
import java.util.Map;

public interface IProduct {
	public Connection productDBConnection();

	public String insertProduct(String productTitle, String productDescription, String productType,
			String productCategory, int researcherId);


	public String updateProduct(String productId, String productTitle, String productDescription, String productType,
			String productCategory);

	public String readAllProducts() ;
	
	public Map<String, Object> getProductByType(String productType);

	public String deleteProduct(String productID);

	public Map<String, Object> getSpecificProduct(int productId);
	
	public Map<String, Object> getProductsOfResearcher(int researcherId);

}
