package contract;

public enum ContractRunState {
    READY("READY"), // contractList에서 대기중인 contract 상태
    FINISH("FINISH"), // contractList에서 계약이 완료된 contract 상태
	DENY("DENY"); // contractList에서 계약 거절된 상태 - 인수 심사에서의 '거절' 역할

	private String contractRunState;
	ContractRunState( String contractRunState ){
		this.contractRunState = contractRunState;
	}
	public String getString() {
		return this.contractRunState;
	}
}
