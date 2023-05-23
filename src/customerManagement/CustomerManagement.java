package customerManagement;

import customer.Customer;

public class CustomerManagement {
    private Customer customer;
    private String ID;
    private String PW;

    public CustomerManagement(){
        //customer = new Customer();
    }
    public int getCustomerID() { return customerID;}
    public void setCustomerID(int customerID) {this.customerID = customerID;}
    private int customerID;
    public String getID() {return ID;}
    public void setID(String ID) {this.ID = ID;}
    public String getPW() {return PW;}
    public void setPW(String PW) {this.PW = PW;}
}
