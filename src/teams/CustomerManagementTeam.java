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

    public void join(String userId, String password, String customerInf) {
        Customer customer = createCustomer(customerInf);
        int customerId = customerList.add(customer);
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.setID(userId);
        customerManagement.setPW(password);
        customerManagement.setCustomerID(customerId);
        customerManagementList.add(customerManagement);
    }

    private Customer createCustomer(String customerInf) {
        String[] customerInfList = customerInf.split("/");
        Customer customer = new Customer();
        customer.setAddress(customerInfList[0]);
        customer.setAge(Integer.parseInt(customerInfList[1]));
        customer.setSex(Constants.Gender.valueOf(customerInfList[2]));
        customer.setJob(customerInfList[3]);
        customer.setName(customerInfList[4]);
        customer.setPhoneNumber(customerInfList[5]);
        customer.setRegistrationNumber(customerInfList[6]);
        customer.setIncomeLevel(Integer.parseInt(customerInfList[7]));
        customer.setAccountNumber(customerInfList[8]);
        customer.setAccountPassword(customerInfList[9]);
        return customer;
    }
}
