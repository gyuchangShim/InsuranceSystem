package dao;

import customer.Customer;
import customer.CustomerList;
import exception.CustomException;
import util.Constants.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements CustomerList {

	private Dao dao;

	public CustomerDao() {
		try {
			dao = new Dao();
			dao.connect();
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	@Override
	public int add(Customer customer) {
		String query = "insert into Customer(address, sex, age, job, name, phoneNumber, "
				+ "registrationNumber, incomeLevel, accountNumber, accountPassword) values('"
				+ customer.getAddress() + "', '"
				+ customer.getSex() + "', "
				+ customer.getAge() + ", '"
				+ customer.getJob() + "', '"
				+ customer.getName() + "', '"
				+ customer.getPhoneNumber() + "', '"
				+ customer.getRegistrationNumber() + "', "
				+ customer.getIncomeLevel() + ", '"
				+ customer.getAccountNumber() + "','"
				+ customer.getAccountPassword() + "');";
		dao.create(query);
		ResultSet retrieve = dao.retrieve("SELECT LAST_INSERT_ID();");
		try {
			if (retrieve.next()) {
				return retrieve.getInt(1);
			} else {
				throw new RuntimeException("Customer ID를 찾을 수 없습니다.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
			ResultSet retrieve = dao.retrieve(query);
			if (retrieve.next()) {
				return getCustomer(retrieve);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void update(Customer customer) {
		String query = "UPDATE Customer SET address = '" +  customer.getAddress() + "', "
				+ "sex= '"+customer.getSex() + "', "
				+ "age= "+ customer.getAge() + ", "
				+ "job= '"+ customer.getJob() + "', "
				+ "name= '"+ customer.getName() + "', "
				+ "phoneNumber= '"+ customer.getPhoneNumber() + "', "
				+ "registrationNumber= '"+ customer.getRegistrationNumber() + "', "
				+ "incomeLevel= "+ customer.getIncomeLevel() + ", "
				+ "accountNumber= '"+ customer.getAccountNumber() + "',"
				+ "accountPassword= '"+ customer.getAccountPassword()
				+"' WHERE customerID = "+customer.getCustomerID()+";";
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
		String sex = resultSet.getString("sex");
		customer.setSex(dao.enumNullCheck(sex, () -> Gender.valueOf(sex)));
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
