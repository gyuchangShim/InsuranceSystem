package customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerListImpl implements CustomerList {

	private List<Customer> customerList;
	private int customerIdGenerator;

	public CustomerListImpl() {
		customerList = new ArrayList<>();
		customerIdGenerator = 1;
	}

	@Override
	public int add(Customer customer) {
		int customerId = generateCustomerId();
		customer.setCustomerID(customerId);
		customerList.add(customer);
		return customerId;
	}
	@Override
	public void delete(int customerId) {}
	@Override
	public Customer retrieve(int customerId) {
		return null;
	}
	@Override
	public void update(Customer customer) {}

	@Override
	public List<Customer> retrieveAll() {
		return customerList;
	}

	private int generateCustomerId() {
		return customerIdGenerator++;
	}
}
