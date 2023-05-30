package insurance;

public enum InsuranceState {
    PLANED("PLANED"), 
    DESIGNED("DESIGNED"), 
    RATED("RATED"), 
    AUTHORIZED("AUTHORIZED");
	
	private String insuranceState;
	InsuranceState( String insuranceState ){
		this.insuranceState = insuranceState;
	}
	public String getString() {
		return this.insuranceState;
	}
}
