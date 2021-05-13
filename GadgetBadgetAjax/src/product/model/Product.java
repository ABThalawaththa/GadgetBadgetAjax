package product.model;

//Product Model class
/**
 * @author IT19240602
 *
 */
/**
 * @author IT19240602
 *
 */
public class Product {

	private int productId;

	private String productTitle;

	private String productDescription;

	private String productType;

	private String productCategory;

	private int resercherId;

	public Product() {
	}

	/**
	 * @param productId
	 * @param productTitle
	 * @param productDescription
	 * @param productType
	 * @param productCategory
	 */
	public Product(int productId, String productTitle, String productDescription, String productType,
			String productCategory) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productType = productType;
		this.productCategory = productCategory;
	}

	/**
	 * @return
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return
	 */
	public String getProductTitle() {
		return productTitle;
	}

	/**
	 * @param productTitle
	 */
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	/**
	 * @return
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @param productCategory
	 */
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	/**
	 * @return
	 */
	public int getResercherId() {
		return resercherId;
	}

	/**
	 * @param resercherId
	 */
	public void setResercherId(int resercherId) {
		this.resercherId = resercherId;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productTitle=" + productTitle + ", productDescription="
				+ productDescription + ", productType=" + productType + ", productCategory=" + productCategory
				+ ", resercherId=" + resercherId + "]";
	}

	/**
	 * @return
	 */
	public String getProductCategory() {
		return productCategory;
	}

}
