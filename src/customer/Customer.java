package Customer;
import Contract.Contract;

public class Customer {
	
	private String address;
	private int age;
	private int customerID;
	private String job;
	private String name;
	private String phoneNumber;
	private String registrationNumber;
	private int salaryPercentage;
	private String accountNumber;
	
	public Customer(){

	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getSalaryPercentage() {
		return salaryPercentage;
	}

	public void setSalaryPercentage(int salaryPercentage) {
		this.salaryPercentage = salaryPercentage;
	}

	public void finalize() throws Throwable {

	}

	public void apply(int diff){

	}

	public void pay(){

	}

}