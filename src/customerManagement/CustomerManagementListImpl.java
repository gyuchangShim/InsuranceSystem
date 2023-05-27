package customerManagement;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagementListImpl implements CustomerManagementList{

    private List<CustomerManagement> customerManagementList;

    public CustomerManagementListImpl() {
        customerManagementList = new ArrayList<>();
    }

    @Override
    public void add(CustomerManagement customerManagement) {
        customerManagementList.add(customerManagement);
    }

    @Override
    public void delete(int customerManagementId) {

    }

    @Override
    public CustomerManagement retrieve(int customerManagementId) {
        return null;
    }

    @Override
    public List<CustomerManagement> retrieveAll() {
        return customerManagementList;
    }

    @Override
    public void update(CustomerManagement customerManagement) {

    }
}
