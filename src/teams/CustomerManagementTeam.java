package teams;

import customer.Customer;
import customer.CustomerListImpl;
import customerManagement.CustomerManagement;
import customerManagement.CustomerManagementListImpl;
import java.sql.ResultSet;

public class CustomerManagementTeam {

    private CustomerManagement customerManagement;
    private CustomerManagementListImpl customerManagementList;
    private Customer customer;
    private CustomerListImpl customerList;


    public CustomerManagementTeam(){
        customerManagement = new CustomerManagement();
        customerManagementList = new CustomerManagementListImpl(customerManagement);
        customer = new Customer();
        customerList = new CustomerListImpl(customer);
    }
    public ResultSet login(String userId, String password) {
        customerManagement.setID(userId);
        customerManagement.setPW(password);
        customerManagementList.retrieve(customerManagement);
        return customerManagementList.retrieve(customerManagement);
    }

    public int join(String userId, String password, String customerInf) {
        customerManagement.setID(userId);
        customerManagement.setPW(password);
        int customerID = customerManagementList.setCustomerID();
        customerManagement.setCustomerID(customerID);
        customer.setCustomerID(customerID);
        getCustomerInf(customerInf);
        customerList.add(customer);
        customerManagementList.add(customerManagement);
        return customerID;
    }

    private void getCustomerInf(String customerInf) {
        String[] customerInfList = customerInf.split("/");
        customer.setAddress(customerInfList[0]);
        customer.setAge(Integer.parseInt(customerInfList[1]));
        customer.setJob(customerInfList[2]);
        customer.setName(customerInfList[3]);
        customer.setPhoneNumber(customerInfList[4]);
        customer.setRegistrationNumber(customerInfList[5]);
        customer.setSalaryPercentage(Integer.parseInt(customerInfList[6]));
    }
}
