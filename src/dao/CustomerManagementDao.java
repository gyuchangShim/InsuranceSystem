package dao;

import customerManagement.CustomerManagement;
import customerManagement.CustomerManagementList;
import exception.CustomException;
import insurance.Insurance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerManagementDao implements CustomerManagementList {

    private Dao dao;

    public CustomerManagementDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            throw new CustomException(e);

        }
    }

    @Override
    public void add(CustomerManagement customerManagement) {
        String query = "INSERT INTO CustomerManagement(customerID, ID, PW) VALUES("
                + customerManagement.getCustomerID() + ", '"
                + customerManagement.getID() + "', '"
                + customerManagement.getPW() + "');";
        dao.create(query);
    }

    @Override
    public void delete(int customerManagementId) {
        String query = "DELETE FROM CustomerManagement WHERE customerManagementID = " + customerManagementId + ";";
        dao.delete(query);
    }

    @Override
    public CustomerManagement retrieve(int customerManagementId) {
        String query = "SELECT * FROM CustomerManagement WHERE customerManagementID = " + customerManagementId + ";";
        try {
            ResultSet resultSet = dao.retrieve(query);
            if (resultSet.next()) {
                return getCustomerManagement(resultSet);
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomerManagement> retrieveAll() {
        String query = "SELECT * FROM CustomerManagement;";
        try {
            ResultSet resultSet = dao.retrieve(query);
            List<CustomerManagement> customerManagementList = new ArrayList<>();
            while(resultSet.next()) {
                customerManagementList.add(getCustomerManagement(resultSet));
            }
            return customerManagementList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CustomerManagement customerManagement) {
        String query = "UPDATE CustomerManagement SET customerID = " + customerManagement.getCustomerID()
                + ", ID = '" + customerManagement.getID()
                + "', PW = '" + customerManagement.getPW()
                + "' WHERE customerManagementID = " + customerManagement.getCustomerManagementID() + ";";
        dao.update(query);
    }

    private CustomerManagement getCustomerManagement(ResultSet retrieve) throws SQLException{
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.setCustomerManagementID(retrieve.getInt("customerManagementID"));
        customerManagement.setCustomerID(retrieve.getInt("customerID"));
        customerManagement.setID(retrieve.getString("ID"));
        customerManagement.setPW(retrieve.getString("PW"));
        return customerManagement;
    }
}
