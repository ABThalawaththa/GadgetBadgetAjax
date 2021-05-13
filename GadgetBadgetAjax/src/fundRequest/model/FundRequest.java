package fundRequest.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

//fund request model class

@XmlRootElement
public class FundRequest {

	private int fundID;

	private String clientID;

	private int productID;

	private String contactName;

	private String contactNo;

	private String contactMail;

	private String message;

	private String orgName;

	private Date date;

	public FundRequest() {

	}

	public FundRequest(int fundID, String clientID, int productID, String contactName, String contactNo,
			String contactMail, String message, String orgName, Date date) {
		super();
		this.fundID = fundID;
		this.clientID = clientID;
		this.productID = productID;
		this.contactName = contactName;
		this.contactNo = contactNo;
		this.contactMail = contactMail;
		this.message = message;
		this.orgName = orgName;
		this.date = date;
	}

	public int getFundID() {
		return fundID;
	}

	public void setFundID(int fundID) {
		this.fundID = fundID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
