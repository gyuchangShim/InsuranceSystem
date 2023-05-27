package contract;

import util.Constants.Result;

public class AdviceNote {
	
	private int adviceNoteID;
	private String content;
	private int customerID;
	private Result result;
	
	public int getAdviceNoteID() {
		return adviceNoteID;
	}
	public void setAdviceNoteID(int adviceNoteID) {
		this.adviceNoteID = adviceNoteID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	

}
