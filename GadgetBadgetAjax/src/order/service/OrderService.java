package order.service;


import java.util.List;

import order.model.Order;

/**
 * Interface for the implementation of CRUD operations
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 * 
 */
public interface OrderService {
	
	/**
	 * Add new order
	 * @param order
	 * @return output message
	 */
	public String createOrder(Order order);

	/**
	 * Get all details of the orders
	 * @return order details
	 */
	public String getAllOrders();
	
	/**
	 * Update the relevant order
	 * @param order
	 * @return the output message
	 */
	public String updateOrder(Order order);

	/**
	 * Delete the relevant order
	 * @param orderId
	 * @return the output message
	 */
	public String deleteOrder(String orderId);

	/**
	 * Get details of the order by id
	 * @param orderId
	 * @return order details per id
	 * 
	 */
	public Order getOrderById(int orderId);
	
	/**
	 * Get details of the order by project id
	 * @param projectId
	 * @return order details for specific project
	 */
	public List<Order> getOrdersByProjectId(int projectId);
	
	/**
	 * Update the status of order
	 * @param orderId
	 * @param status
	 * @return output message
	 */
	public String updateOrderStatus(int orderId, String status);

}
