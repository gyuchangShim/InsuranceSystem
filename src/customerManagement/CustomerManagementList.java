package customerManagement;

import java.util.List;

public interface CustomerManagementList {
    void add(CustomerManagement customerManagement);
    void delete(int customerManagementId);
    CustomerManagement retrieve(int customerManagementId);
    List<CustomerManagement> retrieveAll();
    void update(CustomerManagement customerManagement);
}
