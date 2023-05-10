package Contract;
import java.io.File;
import java.util.Date;

public class Contract {

	private Date contractDate;
	private File contractFile;
	private int contractID;
	private int customerID;
	private int insuranceID;

	public Contract(){

	}
	
	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public File getContractFile() {
		return contractFile;
	}

	public void setContractFile(File contractFile) {
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

	public void finalize() throws Throwable {

	}

}