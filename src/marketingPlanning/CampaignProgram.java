package marketingPlanning;

public class CampaignProgram {

	private int budget; // 캠페인 예산
	private int campaignID; // 캠페인 ID - DB 자동 생성
	private String campaignName; // 캠페인 이름
	private String campaignTarget; // 캠페인 대상
	private int duration; // 캠페인 기간
	private float exResult; // 캠페인 예상 손익률
	private int insuranceID; // 캠페인 대상 보험
	private String place; // 캠페인 장소
	private String campaignWay; // 캠페인 수단 - 시나리오에 적혀있지만 설계 과정에서 attribute가 제외되서 추가함
	private float endResult; // 실제 손익률 - 시나리오에 적혀있지만 설계 과정에서 attribute가 제외되서 추가함
	private CampaignProgram report; // 캠페인 결과 분석 보고서 - 시나리오에 적혀있지만 설계에서 제외됨
	private CampaignState state;
	private String outTeam;

	public CampaignProgram(){
		state = null;
		endResult = 5.5f;
		outTeam = "현우 없는 현우 팀";
	}

	public void setProgramState(CampaignState campaignState) {
		this.state = campaignState;
	}

	public void setOutTeam(String outTeam) {outTeam = outTeam;}
	public String getOutTeam() {return outTeam;}

	public void setEndResult(float endResult) {this.endResult = endResult;}
	public CampaignState getProgramState() { return state; }
	public float getEndResult() { return endResult; }
	public void setReport(CampaignProgram report) { this.report = report; }

	public CampaignProgram getReport() { return report; }

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

	public float getExResult() {
		return exResult;
	}

	public void setExResult(float exResult) {
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