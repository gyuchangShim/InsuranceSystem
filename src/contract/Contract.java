package contract;

import java.time.LocalDateTime;

public class Contract {

	private LocalDateTime contractDate;
	private String contractFile;
	private int contractID;
	private int customerID;
	private int insuranceID;
	private String specialization;		// 유저와 계약간의 특이사항
	
	public LocalDateTime getContractDate() {
		return contractDate;
	}

	public void setContractDate(LocalDateTime contractDate) {
		this.contractDate = contractDate;
	}

	public String getContractFile() {
		return contractFile;
	}

	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
	}

	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}

	public void setSpecialization( String specialization ) {
		this.specialization = specialization;
	}
	
	public String getSpecialization() {
		return this.specialization;
	}

}