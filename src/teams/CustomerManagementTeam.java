package teams;

import customer.Customer;
import customer.CustomerList;
import customerManagement.CustomerManagement;
import customerManagement.CustomerManagementList;
import customerManagement.CustomerManagementListImpl;
import exception.CIllegalArgumentException;
import java.util.Optional;

public class CustomerManagementTeam {

    private CustomerManagementList customerManagementList;
    private CustomerList customerList;

    public CustomerManagementTeam(CustomerManagementList customerManagementList, CustomerList customerList){
        this.customerManagementList = customerManagementList;
        this.customerList = customerList;
    }

    public int login(String userId, String password) {
        Optional<CustomerManagement> findCustomer = customerManagementList.retrieveAll()
            .stream()
            .filter(customerManagement -> customerManagement.getID().equals(userId) &&
                customerManagement.getPW().equals(password))
            .findFirst();
        if (findCustomer.isPresent()) {
            return findCustomer.get().getCustomerID();
        }
        throw new CIllegalArgumentException("로그인을 실패했습니다.");
    }

    public void join(String userId, String password, String customerName) {
        Customer customer = createCustomer(customerName);
        int customerId = customerList.add(customer);
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.setID(userId);
        customerManagement.setPW(password);
        customerManagement.setCustomerID(customerId);
        customerManagementList.add(customerManagement);
    }

    private Customer createCustomer(String customerName) {
        Customer customer = new Customer();
        customer.setName(customerName);
        return customer;
    }
}
