package marketingPlanning;

public class CampaignProgram {

	private int budget; // 캠페인 예산
	private int campaignID; // 캠페인 ID - DB 자동 생성
	private String campaignName; // 캠페인 이름
	private String campaignTarget; // 캠페인 대상
	private int duration; // 캠페인 기간
	private int exResult; // 캠페인 예상 손익률
	private int insuranceID; // 캠페인 대상 보험
	private String place; // 캠페인 장소
	private String campaignWay; // 캠페인 수단 - 시나리오에 적혀있지만 설계 과정에서 attribute가 제외되서 추가함
	private campaignState campaignState;

	public CampaignProgram(){
		campaignState = null;
	}

	public enum campaignState{
		Plan, // 기획 완료
		Run, // 진행 중
		End // 종료
	}
	public void setProgramState(campaignState campaignState) {
		this.campaignState = campaignState;
	}
	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getCampaignID() {
		return campaignID;
	}

	public void setCampaignID(int campaignID) {
		this.campaignID = campaignID;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaignTarget() {
		return campaignTarget;
	}

	public void setCampaignTarget(String campaignTarget) {
		this.campaignTarget = campaignTarget;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getExResult() {
		return exResult;
	}

	public void setExResult(int exResult) {
		this.exResult = exResult;
	}

	public int getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setCampaignWay(String way) { this.campaignWay = way;}

	public String getCampaignWay() {return campaignWay;}

	public void finalize() throws Throwable {

	}

	public void calculate(){

	}

}