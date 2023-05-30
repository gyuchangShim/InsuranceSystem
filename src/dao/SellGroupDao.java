package dao;

import business.SellGroup;
import business.SellGroupList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellGroupDao implements SellGroupList {
    private Dao dao;

    public SellGroupDao(){
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            e.printStackTrace();
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
        String query = "SELECT * from SellGroup where sellGroupId="+sellGroupId;
        try {
            return getSellGroup(dao.retrieve(query));
        } catch (SQLException e) {throw new RuntimeException(e);}
    }

    @Override
    public void update(SellGroup sellGroup) {
        String query = "UPDATE SellGroup SET exResult = '"+sellGroup.getExResult()+"' WHERE groupID = "+sellGroup.getGroupID()+";";
        dao.update(query);
        query = "UPDATE SellGroup SET name = '"+sellGroup.getName()+"' WHERE groupID = "+sellGroup.getGroupID()+";";
        dao.update(query);
        query = "UPDATE SellGroup SET representative = '"+sellGroup.getRepresentative()+"' WHERE groupID = "+sellGroup.getGroupID()+";";
        dao.update(query);
        query = "UPDATE SellGroup SET representativePhoneNumber = '"+sellGroup.getRepresentativePhoneNumber()+"' WHERE groupID = "+sellGroup.getGroupID()+";";
        dao.update(query);
    }

    @Override
    public List<SellGroup> retrieveAll() {
        String query = "select * from SellGroup;";
        try {
        ResultSet resultSet = dao.retrieve(query);
        List<SellGroup> sellGroupList = new ArrayList<>();

            while(resultSet.next()) {
                System.out.println(resultSet.getInt("groupID"));
                sellGroupList.add(getSellGroup(resultSet));

            }
            return sellGroupList;
        } catch (SQLException e) {throw new RuntimeException(e);}
    }

    private SellGroup getSellGroup(ResultSet resultSet) throws SQLException {
        SellGroup sellGroup= new SellGroup();
        while (resultSet.next()){
            sellGroup.setGroupID(resultSet.getInt("groupID"));
            sellGroup.setExResult(resultSet.getString("exResult"));
            sellGroup.setName(resultSet.getString("name"));
            sellGroup.setRepresentative(resultSet.getString("representative"));
            sellGroup.setRepresentativePhoneNumber(resultSet.getString("representativePhoneNumber"));
        }
        return sellGroup;
    }
}
