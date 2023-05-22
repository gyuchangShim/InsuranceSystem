package contractManagement;

public class ContractManagementPolicy {

	private String content;
	private String name;
	private int policyID;

	public ContractManagementPolicy(){

	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPolicyID() {
		return policyID;
	}

	public void setPolicyID(int policyID) {
		this.policyID = policyID;
	}

	public void finalize() throws Throwable {

	}

}