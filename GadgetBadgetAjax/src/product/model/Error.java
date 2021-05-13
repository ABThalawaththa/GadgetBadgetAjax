package product.model;

//Handle Error Messages
public class Error {
	private String errorMessage;
	
	public Error() {
		
	}

	public Error(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
