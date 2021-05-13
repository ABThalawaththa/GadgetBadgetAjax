package order.model;


import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Order model class
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 *
 */

@XmlRootElement
public class Order {
	
	private int orderId;
    private String orderDesc;
    private Date orderDate;
    private String orderStatus;
    private int productId;
    private int buyerId;
    
    // default constructor
    public Order() {
		super();
	}

	// overloaded constructor
    public Order(int orderId, String orderDesc, Date orderDate, String orderStatus, int productId, int buyerId) {
		super();
		this.orderId = orderId;
		this.orderDesc = orderDesc;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.productId = productId;
		this.buyerId = buyerId;
	}

	// constructor with parameters
    public Order(String orderDesc, String orderStatus, int productId, int buyerId) {
		super();
		this.orderDesc = orderDesc;
		this.orderStatus = orderStatus;
		this.productId = productId;
		this.buyerId = buyerId;
	}
    

	public Order(int orderId, String orderDesc, String orderStatus, int productId, int buyerId) {
		super();
		this.orderId = orderId;
		this.orderDesc = orderDesc;
		this.orderStatus = orderStatus;
		this.productId = productId;
		this.buyerId = buyerId;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * @return the orderDesc
	 */
	public String getOrderDesc() {
		return orderDesc;
	}
	
	/**
	 * @param orderDesc the orderDesc to set
	 */
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * @return the buyerId
	 */
	public int getBuyerId() {
		return buyerId;
	}
	
	/**
	 * @param buyerId the buyerId to set
	 */
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
    

}
