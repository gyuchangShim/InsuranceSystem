package business;

public class OperationPolicy {
	private String content;
	private String name;
	private int policyID;
	private int rating;
	private int pass;

	public OperationPolicy(){}
	
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getPolicyID() {return policyID;}
	public void setPolicyID(int policyID) {this.policyID = policyID;}
	public int getRating() {return rating;}
	public void setRating(int rating) {this.rating = rating;}
	public int getPass() {return pass;}
	public void setPass(int pass) {this.pass = pass;}
}
