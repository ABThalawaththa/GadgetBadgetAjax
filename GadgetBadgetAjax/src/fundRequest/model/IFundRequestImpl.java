package fundRequest.model;

import java.util.List;

public interface IFundRequestImpl {

	String readFundRequests();

	FundRequest getFundRequest(int id);

	String insertRequest(int productID, String contactName, String contactNo, String contactMail,
			String message, String orgName);

	String deleteRequest(int fundID);

	String updateRequest(int fundID, String clientID, int productID, String contactName, String contactNo,
			String contactMail, String message, String orgName);

	List<FundRequest> getAllRequests();

	List<FundRequest> getRequestByProducId(int id);
}
