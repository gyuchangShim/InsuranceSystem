package insurance;

import Contract.Contract;
import Reward.Reward;

public class Insurance {

	private String planReport;

	private int insuranceID;
	private String canRegistTarget;
	private int duration;
	private String insuranceName;
	private int payment;
	private int rate;
	private int resultAnalysis;
	private int rewardAmount;
	private int salesPerformance;
	
	public Contract m_Contract;
	public Reward m_Reward;

	public Insurance(){

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
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
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
	public void finalize() throws Throwable {}
	public String getPlanReport() {
		return planReport;
	}
	public void setPlanReport(String planReport) {
		this.planReport = planReport;
	}
	public void apply(int diff){}
}