package teams;

import customer.Customer;
import customer.CustomerList;
import customerManagement.CustomerManagement;
import customerManagement.CustomerManagementList;
import exception.CIllegalArgumentException;
import util.Constants;

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

    public void register(String userId, String password, String[] customerInf) {
        Customer customer = setCustomer(customerInf);
        int customerId = customerList.add(customer);
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.setID(userId);
        customerManagement.setPW(password);
        customerManagement.setCustomerID(customerId);
        customerManagementList.add(customerManagement);
    }

    private Customer setCustomer(String[] customerInf) {
        Customer customer = new Customer();
        customer.setAddress(customerInf[0]);
        customer.setAge(Integer.parseInt(customerInf[1]));
        customer.setSex(Constants.Gender.valueOf(customerInf[2]));
        customer.setJob(customerInf[3]);
        customer.setName(customerInf[4]);
        customer.setPhoneNumber(customerInf[5]);
        customer.setRegistrationNumber(customerInf[6]);
        customer.setIncomeLevel(Integer.parseInt(customerInf[7]));
        customer.setAccountNumber(customerInf[8]);
        customer.setAccountPassword(customerInf[9]);
        return customer;
    }
}
