package dao;

import customer.Customer;
import customer.CustomerList;
import util.Constants.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements CustomerList {

	private Dao dao;
	private Customer customer;


	public CustomerDao(Customer customer) {
		this.customer = customer;
		try {
			dao = new Dao();
			dao.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int add(Customer customer) {
		String query = "insert into CustomerList(address,sex,age,job,name,phoneNumber,"
				+ "registrationNumber,incomeLevel,accountNumber,accountPassword) values('"
				+ customer.getAddress() + "', "
				+ customer.getSex() + "', "
				+ customer.getAge() + ", '"
				+ customer.getJob() + "', '"
				+ customer.getName() + "', '"
				+ customer.getPhoneNumber() + "', '"
				+ customer.getRegistrationNumber() + "', '"
				+ customer.getIncomeLevel() + ", '"
				+ customer.getAccountNumber() + "','"
				+ customer.getAccountPassword() + "');";
		dao.create(query);
		return customer.getCustomerID();
	}

	@Override
	public void delete(int customerId) {
		String query = "DELETE FROM Customer WHERE customerId = " + customerId + ";";
		dao.delete(query);
	}
	@Override
	public Customer retrieve(int customerId) {
		String query = "SELECT * FROM Customer WHERE customerId = " + customerId + ";";
		try {
			return getCustomer(dao.retrieve(query));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void update(Customer customer) {
		String query = "UPDATE CustomerList SET address = '" +  customer.getAddress() + "', "
				+ "sex= '"+customer.getSex() + "', "
				+ "age= "+ customer.getAge() + ", "
				+ "job= '"+ customer.getJob() + "', "
				+ "name= '"+ customer.getName() + "', "
				+ "phoneNumber= '"+ customer.getPhoneNumber() + "', "
				+ "registrationNumber= '"+ customer.getRegistrationNumber() + "', "
				+ "incomeLevel= "+ customer.getIncomeLevel() + ", "
				+ "accountNumber= '"+ customer.getAccountNumber() + "',"
				+ "accountPassword= '"+ customer.getAccountPassword() + "');";
		dao.update(query);
	}

	@Override
	public List<Customer> retrieveAll() {
		String query = "SELECT * FROM Customer;";
		try {
			ResultSet resultSet = dao.retrieve(query);
			List<Customer> customerList = new ArrayList<>();
			while(resultSet.next()) {
				customerList.add(getCustomer(resultSet));
			}
			return customerList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Customer getCustomer(ResultSet resultSet) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerID(resultSet.getInt("customerID"));
		customer.setAddress(resultSet.getString("address"));
		customer.setSex(Gender.valueOf(resultSet.getString("sex")));
		customer.setAge(resultSet.getInt("age"));
		customer.setJob(resultSet.getString("job"));
		customer.setName(resultSet.getString("name"));
		customer.setPhoneNumber(resultSet.getString("phoneNumber"));
		customer.setRegistrationNumber(resultSet.getString("registrationNumber"));
		customer.setIncomeLevel(resultSet.getInt("incomeLevel"));
		customer.setAccountNumber(resultSet.getString("accountNumber"));
		customer.setAccountPassword(resultSet.getString("accountPassword"));
		return customer;
	}
}