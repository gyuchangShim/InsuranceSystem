package customer;

import customer.Customer;
import java.sql.ResultSet;

public interface CustomerList {
     void add(Customer customer);
     void delete(Customer customer);
     Customer retrieve(Customer customer);
     void update(Customer customer);

     int getCustomerID();
}
