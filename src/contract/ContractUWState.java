package contract;

public enum ContractUWState {
    BASIC("BASIC"), // 일반 인수 - 인수 심사에서의 일반 인수 역할
    COLLABORATIVE("COLLABORATIVE"); // 공동 인수 - 인수 심사에서의 보완 역할
	
	private String contractUWState;
	
	ContractUWState( String contractUWState){
		this.contractUWState = contractUWState;
	}
	public String getString() {
		return this.contractUWState;
	}
}
