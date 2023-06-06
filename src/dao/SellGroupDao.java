package dao;

import business.SellGroup;
import business.SellGroupList;

import exception.CustomException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellGroupDao implements SellGroupList {
    private Dao dao;

    public SellGroupDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public void add(SellGroup sellGroup) {

    }

    @Override
    public void delete(int sellGroupId) {

    }

    @Override
    public SellGroup retrieve(int sellGroupId) {
        String query = "SELECT * from SellGroup where sellGroupId=" + sellGroupId;
        try {
            ResultSet resultSet = dao.retrieve(query);
            if (resultSet.next()) {
                return getSellGroup(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(SellGroup sellGroup) {
        String query = "UPDATE SellGroup SET "
                + "exResult = '" +sellGroup.getExResult() + "', "
                + "name = '"+ sellGroup.getName() + "', "
                + "representative = '" +sellGroup.getRepresentative() + "', "
                + "representativePhoneNumber = '" +sellGroup.getRepresentativePhoneNumber() + "' "
                + "WHERE sellGroupId = " + sellGroup.getGroupID() + ";";
        dao.update(query);
    }

    @Override
    public List<SellGroup> retrieveAll() {
        String query = "select * from SellGroup;";
        try {
            ResultSet resultSet = dao.retrieve(query);
            List<SellGroup> sellGroupList = new ArrayList<>();
            while (resultSet.next()) {
                sellGroupList.add(getSellGroup(resultSet));
            }
            return sellGroupList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SellGroup getSellGroup(ResultSet resultSet) throws SQLException {
        SellGroup sellGroup = new SellGroup();
        sellGroup.setGroupID(resultSet.getInt("sellGroupID"));
        sellGroup.setExResult(resultSet.getString("exResult"));
        sellGroup.setName(resultSet.getString("name"));
        sellGroup.setRepresentative(resultSet.getString("representative"));
        sellGroup.setRepresentativePhoneNumber(resultSet.getString("representativePhoneNumber"));
        return sellGroup;
    }
}
