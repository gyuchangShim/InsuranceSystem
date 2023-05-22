package reward;
import java.util.Date;

import util.Constants;

public class Reward {
	private Constants.Result appliResult;
	private String accidentProfile;			// File 형식이 TUI에서는 지원되지 않으므로 String으로 대신함
	private Date appliDate;
	private String content;
	private int contractID;
	private int customerName;
	private String identifyProfile;			// File 형식이 TUI에서는 지원되지 않으므로 String으로 대신함
	private int reward;						// 보상금
	private int rewardID;

	public Reward(){}
	public Constants.Result getAppliResult(){ return this.appliResult; }
	public void setAppliResult( Constants.Result appliResult ) { this.appliResult = appliResult; }
	public String getAccidentProfile() {return accidentProfile;}
	public void setAccidentProfile(String accidentProfile) {this.accidentProfile = accidentProfile;}
	public Date getAppliDate() {return appliDate;}
	public void setAppliDate(Date appliDate) {this.appliDate = appliDate;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public int getContractID() {return contractID;}
	public void setContractID(int contractID) {this.contractID = contractID;}
	public int getCustomerName() {return customerName;}
	public void setCustomerName(int customerName) {this.customerName = customerName;}
	public String getIdentifyProfile() {return identifyProfile;}
	public void setIdentifyProfile(String identifyProfile) {this.identifyProfile = identifyProfile;}
	public int getReward() {return reward;}
	public void setReward(int reward) {this.reward = reward;}
	public int getRewardID() {return rewardID;}
	public void setRewardID(int rewardID) {this.rewardID = rewardID;}
}