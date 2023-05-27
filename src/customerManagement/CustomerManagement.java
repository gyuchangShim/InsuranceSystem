package customerManagement;

public class CustomerManagement {

    private int customerManagementId;
    private int customerId;
    private String ID;
    private String PW;


    public String getID() {return ID;}
    public void setID(String ID) {this.ID = ID;}
    public String getPW() {return PW;}
    public void setPW(String PW) {this.PW = PW;}

    public int getCustomerManagementId() {
        return customerManagementId;
    }

    public void setCustomerManagementId(int customerManagementId) {
        this.customerManagementId = customerManagementId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
