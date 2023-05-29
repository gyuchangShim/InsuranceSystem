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
//        String query = "SELECT * from SellGroupList where name='"+sellGroup.getName()+"'";
//        ResultSet resultSet = dao.retrieve(query);
//        try {
//            while (resultSet.next()){
//                sellGroup.setGroupID(resultSet.getInt("groupID"));
//                sellGroup.setExResult(resultSet.getString("exResult"));
//                sellGroup.setName(resultSet.getString("name"));
//                sellGroup.setRepresentative(resultSet.getString("representative"));
//                sellGroup.setRepresentativePhoneNumber(resultSet.getString("representativePhoneNumber"));
//            }
//        } catch (SQLException e) {throw new RuntimeException(e);}
//        return sellGroup;
        return null;
    }

    @Override
    public void update(SellGroup sellGroup) {
        String query = "UPDATE SellGroupList SET exResult = '"+sellGroup.getExResult()+"' WHERE groupID = "+sellGroup.getGroupID()+";";
        dao.update(query);
        query = "UPDATE SellGroupList SET name = '"+sellGroup.getName()+"' WHERE groupID = "+sellGroup.getGroupID()+";";
        dao.update(query);
        query = "UPDATE SellGroupList SET representative = '"+sellGroup.getRepresentative()+"' WHERE groupID = "+sellGroup.getGroupID()+";";
        dao.update(query);
        query = "UPDATE SellGroupList SET representativePhoneNumber = '"+sellGroup.getRepresentativePhoneNumber()+"' WHERE groupID = "+sellGroup.getGroupID()+";";
        dao.update(query);
    }

    @Override
    public List<SellGroup> retrieveAll() {
        String query = "SELECT * from SellGroupList";
        ResultSet resultSet = dao.retrieve(query);
        List<SellGroup> sellGroupList = new ArrayList<>();
        int i = 0;

        try {
            while (resultSet.next()){
                sellGroupList.add(new SellGroup());
                sellGroupList.get(i).setGroupID(resultSet.getInt("groupID"));
                sellGroupList.get(i).setExResult(resultSet.getString("exResult"));
                sellGroupList.get(i).setName(resultSet.getString("name"));
                sellGroupList.get(i).setRepresentative(resultSet.getString("representative"));
                sellGroupList.get(i).setRepresentativePhoneNumber(resultSet.getString("representativePhoneNumber"));
                i++;
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return sellGroupList;
    }
}
