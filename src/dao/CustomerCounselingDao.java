package dao;

import customer.CounselingState;
import customer.CustomerCounseling;
import customer.CustomerCounselingList;
import exception.CustomException;
import insurance.Insurance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import util.DateConverter;

public class CustomerCounselingDao implements CustomerCounselingList {

    private Dao dao;

    public CustomerCounselingDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public void add(CustomerCounseling counseling) {
        String sql = "INSERT INTO CustomerCounseling (counselingID, customerID, counselingPlace,"
            + " counselingTime, counselingState) VALUES ("
            + counseling.getCounselingId() + ", "
            + counseling.getCustomerId() + ", '"
            + counseling.getCounselingPlace() + "', '"
            + counseling.getCounselingTime() + "', '"
            + counseling.getCounselingState() + "');";
        dao.create(sql);
    }

    @Override
    public void delete(int counselingId) {
        String sql = "DELETE FROM CustomerCounseling WHERE counselingID = " + counselingId + ";";
        dao.delete(sql);
    }

    @Override
    public CustomerCounseling retrieve(int counselingId) {
        String query = "SELECT * FROM CustomerCounseling WHERE insuranceID = " + counselingId + ";";
        try {
            ResultSet resultSet = dao.retrieve(query);
            if (resultSet.next()) {
                return getCounseling(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomerCounseling> retrieveAll() {
        String query = "SELECT * FROM CustomerCounseling;";
        try {
            ResultSet resultSet = dao.retrieve(query);
            List<CustomerCounseling> customerCounselingList = new ArrayList<>();
            while(resultSet.next()) {
                customerCounselingList.add(getCounseling(resultSet));
            }
            return customerCounselingList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CustomerCounseling counseling) {
        String query = "UPDATE CustomerCounseling SET "
                + "customerID = " + counseling.getCustomerId() + ", "
                + "counselingPlace = '" + counseling.getCounselingPlace() + "', "
                + "counselingTime = " + DateConverter.toString(counseling.getCounselingTime()) + ", "
                + "counselingState = '" + counseling.getCounselingState() + "' "
                + "WHERE counselingID = " + counseling.getCounselingId() + ";";
        dao.update(query);
    }

    private CustomerCounseling getCounseling(ResultSet resultSet) throws SQLException {
        CustomerCounseling counseling = new CustomerCounseling();
        counseling.setCounselingId(resultSet.getInt("counselingID"));
        counseling.setCustomerId(resultSet.getInt("customerID"));
        counseling.setCounselingPlace(resultSet.getString("counselingPlace"));
        Timestamp counselingTime = resultSet.getTimestamp("counselingTime");
        counseling.setCounselingTime(dao.dateTimeNullCheck(counselingTime));
        String counselingState = resultSet.getString("counselingState");
        counseling.setCounselingState(dao.enumNullCheck(counselingState, () -> CounselingState.valueOf(counselingState)));
        return counseling;
    }
}
