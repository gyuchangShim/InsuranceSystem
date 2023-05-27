package customer;

import java.util.List;

public interface CustomerList {
     int add(Customer customer);
     void delete(int customerId);
     Customer retrieve(int customerId);
     void update(Customer customer);
     List<Customer> retrieveAll();
}
