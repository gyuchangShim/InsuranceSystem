package marketingPlanning;

public enum CampaignState {
    PLAN("PLAN"), // 기획 완료
    RUN("RUN"), // 진행 중
    END("END"); // 종료
	
	private String campaignState;
	CampaignState( String campaignState ){
		this.campaignState = campaignState;
	}
	
	public String getString() {
		return this.campaignState;
	}
}