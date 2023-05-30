package dao;

import customer.Customer;
import customer.CustomerList;

import java.util.List;

public class CustomerDao implements CustomerList {

    private Dao dao;
    public CustomerDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int add(Customer customer) {
        return 0;
    }

    @Override
    public void delete(int customerId) {
        String query = "DELETE FROM Customer WHERE cutomerID = " + customerId + ";";
        dao.delete(query);
    }

    @Override
    public Customer retrieve(int customerId) {
        String query = "SELECT * FROM Customer WHERE customerID = " + customerId + ";";
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public List<Customer> retrieveAll() {
        return null;
    }
}
