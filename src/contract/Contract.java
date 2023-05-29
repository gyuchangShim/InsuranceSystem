package contract;

import java.time.LocalDate;

public class Contract {

	private LocalDate contractDate;
	private String contractFile;
	private int contractID;
	private int customerID;
	private int insuranceID;
	private ContractState contractState;
	private ContractRunState contractRunState;
	private ContractUWState contractUWState;

	public Contract(){
		contractState = null;
		contractRunState = null;
		contractUWState = null;
	}

	public void setContractState(ContractState state) {
		contractState = state;
	}

	public ContractState getContractState() {
		return this.contractState;
	}

	public void setContractUWState(ContractUWState state) {
		contractUWState = state;
	}

	public ContractUWState getContractUWState() {
		return this.contractUWState;
	}

	public void setContractRunState(ContractRunState state) {
		contractRunState = state;
	}

	public ContractRunState getContractRunState() {
		return this.contractRunState;
	}
	private String specialization;		// 유저와 계약간의 특이사항

	public LocalDate getContractDate() {
		return contractDate;
	}

	public void setContractDate(LocalDate contractDate) {
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