package customerManagement;

import java.sql.ResultSet;

public interface CustomerManagementList {
    void add(CustomerManagement customerManagement);
    void delete(CustomerManagement customerManagement);
    ResultSet retrieve(CustomerManagement customerManagement);
    void update(CustomerManagement customerManagement);
}
