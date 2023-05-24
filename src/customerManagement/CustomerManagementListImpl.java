package customerManagement;


import dao.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManagementListImpl implements CustomerManagementList {
    private Dao dao;
    private CustomerManagement customerManagement;


    public CustomerManagementListImpl(CustomerManagement customerManagement) {
        this.customerManagement = customerManagement;
        try {
//            dao = new Dao();
//            dao.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(CustomerManagement customerManagement) {
        String query = "insert into CustomerManagementList(CustomerID,ID,PW) values("
                + (customerManagement.getCustomerID()+1) + ",'"
                + customerManagement.getID() + "', '"
                + customerManagement.getPW() +  "');";
        dao.create(query);
    }

    @Override
    public void delete(CustomerManagement customerManagement) {

    }


    @Override
    public ResultSet retrieve(CustomerManagement customerManagement) {
        String query = "SELECT customerID from CustomerManagementList WHERE ID='"
                + customerManagement.getID() + "' and PW='"
                + customerManagement.getPW() +  "';";
        ResultSet resultSet = dao.retrieve(query);
        return resultSet;
    }

    @Override
    public void update(CustomerManagement CustomerManagement) {

    }

    public int setCustomerID(){
        String query = "SELECT customerID from CustomerManagementList";
        ResultSet resultSet = dao.retrieve(query);
        try {
            int a=0;
            while (resultSet.next()) {a = resultSet.getInt("customerID");}
            return a;
        } catch (SQLException e) {throw new RuntimeException(e);}
    }
}
