package Customer;

import dao.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerListImpl implements CustomerList {

	private Dao dao;
	private Customer customer;


	public CustomerListImpl(Customer customer) {
		this.customer = customer;
		try {
			dao = new Dao();
			dao.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Customer customer) {
		String query = "insert into CustomerList(customerID,address,age,job,name,phoneNumber,registrationNumber,salaryPercentage) values("
				+ (customer.getCustomerID()+1) + ",'"
				+ customer.getAddress() + "', "
				+ customer.getAge() + ", '"
				+ customer.getJob() + "', '"
				+ customer.getName() + "', '"
				+ customer.getPhoneNumber() + "', '"
				+ customer.getRegistrationNumber() + "', "
				+ customer.getSalaryPercentage() +  ");";
		dao.create(query);
	}
	@Override
	public void delete(Customer customer) {}
	@Override
	public ResultSet retrieve(Customer customer) {
		return null;
	}
	@Override
	public void update(Customer customer) {}
	@Override
	public int getCustomerID(){
		String query = "SELECT customerID from CustomerList";
		ResultSet resultSet = dao.retrieve(query);
		try {
			int a=0;
			while (resultSet.next()) {a = resultSet.getInt("customerID");}
			return a;
		} catch (SQLException e) {throw new RuntimeException(e);}
	}

}
