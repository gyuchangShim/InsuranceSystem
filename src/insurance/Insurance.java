package insurance;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Insurance {

	private int insuranceID;
	private String insuranceName;
	private String planReport;
	private InsuranceState insuranceState;
	private InsuranceType insuranceType;
	private String salesTarget; // 보험 판매 대상
	private String canRegistTarget; // 보험 가입 가능 대상
	private int payment; // 보험료
	private String guarantee; // 보장 내용
	private int estimatedDevelopment; // 개발 예상 비용
	private float estimatedProfitRate; // 예상 손익률
	private int riskDegree; // 위험도
	private LocalDate SalesStartDate; // 판매 시작일
	private LocalDate SalesEndDate; // 판매 종료일
	private int goalPeopleNumber; // 예상 목표 인원
	private String salesMethod; // 판매 방식

	private float rate; // 보험요율
	private int duration;
	private int resultAnalysis;
	private int rewardAmount;
	private int salesPerformance;
	
	public Insurance(){
		this.insuranceState = InsuranceState.PLANED;
	}
	
	public String getCanRegistTarget() {
		return canRegistTarget;
	}
	public void setCanRegistTarget(String canRegistTarget) {
		this.canRegistTarget = canRegistTarget;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getInsuranceID() {
		return insuranceID;
	}
	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getResultAnalysis() {
		return resultAnalysis;
	}
	public void setResultAnalysis(int resultAnalysis) {
		this.resultAnalysis = resultAnalysis;
	}
	public int getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(int rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	public int getSalesPerformance() {
		return salesPerformance;
	}
	public void setSalesPerformance(int salesPerformance) {
		this.salesPerformance = salesPerformance;
	}
	public String getPlanReport() {
		return planReport;
	}
	public void setPlanReport(String planReport) {
		this.planReport = planReport;
	}
	public void apply(int diff){}
	public InsuranceState getInsuranceState() {
		return insuranceState;
	}
	public void setInsuranceState(InsuranceState insuranceState) {
		this.insuranceState = insuranceState;
	}
	public InsuranceType getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getSalesTarget() {
		return salesTarget;
	}
	public void setSalesTarget(String salesTarget) {
		this.salesTarget = salesTarget;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	public int getEstimatedDevelopment() {
		return estimatedDevelopment;
	}
	public void setEstimatedDevelopment(int estimatedDevelopment) {
		this.estimatedDevelopment = estimatedDevelopment;
	}
	public float getEstimatedProfitRate() {
		return estimatedProfitRate;
	}
	public void setEstimatedProfitRate(float estimatedProfitRate) {
		this.estimatedProfitRate = estimatedProfitRate;
	}
	public int getRiskDegree() {
		return riskDegree;
	}
	public void setRiskDegree(int riskDegree) {
		this.riskDegree = riskDegree;
	}
	public LocalDate getSalesStartDate() {
		return SalesStartDate;
	}
	public void setSalesStartDate(LocalDate salesStartDate) {
		SalesStartDate = salesStartDate;
	}
	public LocalDate getSalesEndDate() {
		return SalesEndDate;
	}
	public void setSalesEndDate(LocalDate salesEndDate) {
		SalesEndDate = salesEndDate;
	}
	public int getGoalPeopleNumber() {
		return goalPeopleNumber;
	}
	public void setGoalPeopleNumber(int goalPeopleNumber) {
		this.goalPeopleNumber = goalPeopleNumber;
	}
	public String getSalesMethod() {
		return salesMethod;
	}
	public void setSalesMethod(String salesMethod) {
		this.salesMethod = salesMethod;
	}
}