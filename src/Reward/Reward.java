package Reward;
import java.io.File;
import java.util.Date;

public class Reward {

	@SuppressWarnings("unused")
	private enum appliResult{ Accept, Deny, Process };
	private File accidentProfile;
	private Date appliDate;
	private String content;
	private int contractID;
	private int customerName;
	private File identifyProfile;
	private int reward;
	private int rewardID;

	public Reward(){

	}
	
	public File getAccidentProfile() {
		return accidentProfile;
	}

	public void setAccidentProfile(File accidentProfile) {
		this.accidentProfile = accidentProfile;
	}

	public Date getAppliDate() {
		return appliDate;
	}

	public void setAppliDate(Date appliDate) {
		this.appliDate = appliDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public int getCustomerName() {
		return customerName;
	}

	public void setCustomerName(int customerName) {
		this.customerName = customerName;
	}

	public File getIdentifyProfile() {
		return identifyProfile;
	}

	public void setIdentifyProfile(File identifyProfile) {
		this.identifyProfile = identifyProfile;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int getRewardID() {
		return rewardID;
	}

	public void setRewardID(int rewardID) {
		this.rewardID = rewardID;
	}

	public void finalize() throws Throwable {

	}

}