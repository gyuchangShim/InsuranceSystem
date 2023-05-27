package customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerListImpl implements CustomerList {

	private List<Customer> customerList;


	public CustomerListImpl() {
		customerList = new ArrayList<>();
	}

	@Override
	public void add(Customer customer) {
		customerList.add(customer);
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

}
