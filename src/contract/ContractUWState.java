package contract;

public enum ContractUWState {
    BASIC("BASIC"),
    COLLABORATIVE("COLLABORATIVE");
	
	private String contractUWState;
	
	ContractUWState( String contractUWState){
		this.contractUWState = contractUWState;
	}
	public String getString() {
		return this.contractUWState;
	}
}
