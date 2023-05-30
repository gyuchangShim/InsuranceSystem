package dao;

import customer.Customer;
import customerManagement.CustomerManagement;
import customerManagement.CustomerManagementList;

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
            e.printStackTrace();
        }
    }

    @Override
    public void add(CustomerManagement customerManagement) {
        String query = "insert into CustomerManagement(ID,PW,customerId) values('"
                + customerManagement.getID() + "', '"
                + customerManagement.getPW() + "',"
                + customerManagement.getCustomerID()+");";

        dao.create(query);
    }

    @Override
    public void delete(int customerManagementId) {
        String query = "DELETE FROM customerManagement WHERE customerManagementId = " + customerManagementId + ";";
        dao.delete(query);
    }


    @Override
    public CustomerManagement retrieve(int customerManagementId) {
        String query = "SELECT * from CustomerManagement WHERE customerManagementId="+ customerManagementId + "';";
        try {
            return getCustomerManagement(dao.retrieve(query));
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
    public void update(CustomerManagement CustomerManagement) {
        String query = "UPDATE CustomerList SET customerManagementId = " +  CustomerManagement.getCustomerManagementID() + ", "
                + "ID= '"+ CustomerManagement.getID() + "',"
                + "PW= '"+ CustomerManagement.getPW() + "',"
                + "customerID= "+ CustomerManagement.getCustomerID() + ");";
        dao.update(query);
    }
    private CustomerManagement getCustomerManagement(ResultSet resultSet) throws SQLException {
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.setCustomerManagementID(resultSet.getInt("customerManagementID"));
        customerManagement.setID(resultSet.getString("ID"));
        customerManagement.setPW(resultSet.getString("PW"));
        customerManagement.setCustomerID(resultSet.getInt("customerID"));
        return customerManagement;
    }
}
