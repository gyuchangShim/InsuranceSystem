package Customer;

import java.sql.ResultSet;

public interface CustomerList {
     void add(Customer customer);
     void delete(Customer customer);
     ResultSet retrieve(Customer customer);
     void update(Customer customer);

     int getCustomerID();
}
