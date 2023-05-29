package customerManagement;

public class CustomerManagement {

    private int customerManagementID;
    private int customerID;
    private String ID;
    private String PW;


    public String getID() {return ID;}
    public void setID(String ID) {this.ID = ID;}
    public String getPW() {return PW;}
    public void setPW(String PW) {this.PW = PW;}

    public int getCustomerManagementID() {
        return customerManagementID;
    }

    public void setCustomerManagementID(int customerManagementID) {
        this.customerManagementID = customerManagementID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
