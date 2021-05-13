package product.model;

import java.sql.Connection;
import java.util.Map;

public interface IProduct {
	public Connection productDBConnection();

	public String insertProduct(String productTitle, String productDescription, String productType,
			String productCategory, int researcherId);

	public Map<String, Object> getAllProducts();

	public String updateProduct(int productId, String productTitle, String productDescription, String productType,
			String productCategory);

	public Map<String, Object> getProductByType(String productType);

	public String deleteProduct(int productId);

	public Map<String, Object> getSpecificProduct(int productId);
	
	public Map<String, Object> getProductsOfResearcher(int researcherId);

}
