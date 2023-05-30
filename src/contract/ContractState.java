package contract;

public enum ContractState {
    OFFLINE("OFFLINE"), // 혁이 파트 - 오프라인 보험 가입 신청
    ONLINE("ONLINE"); // 규창 파트 - UW 보험 가입 신청 유스케이스
    
    private String contractState;
    ContractState( String contractState ){
    	this.contractState = contractState;
    }
    public String getString() {
    	return this.contractState;
    }
}
