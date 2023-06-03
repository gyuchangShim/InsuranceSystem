package dao;

import business.OperationPolicy;
import business.OperationPolicyList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationPolicyDao implements OperationPolicyList {
    private Dao dao;

    public OperationPolicyDao(){
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(OperationPolicy operationPolicy) {
        String query = "insert into OperationPolicy (name ,content,rating,pass) values('"
            + operationPolicy.getName() + "', '"
            + operationPolicy.getContent() + "', "
            + operationPolicy.getRating() +","
            +operationPolicy.getPass()+");";
        dao.create(query);
    }

    @Override
    public void delete(int PolicyId) {

    }

    @Override
    public OperationPolicy retrieve(int PolicyId) {
        String query = "SELECT * FROM OperationPolicy WHERE PolicyId = " + PolicyId + ";";
        try {
            ResultSet retrieve = dao.retrieve(query);
            if (retrieve.next()) {
                return getOperationPolicy(retrieve);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(OperationPolicy operationPolicy) {
        String query = "UPDATE OperationPolicy SET name = '" +  operationPolicy.getName() + "', "
                + "content= '"+operationPolicy.getContent() + "', "
                + "rating= "+ operationPolicy.getRating() + ", "
                + "pass= "+ operationPolicy.getPass() + ";";
        dao.update(query);
    }

    @Override
    public List<OperationPolicy> retrieveAll() {
        String query = "SELECT * from OperationPolicy";
        ResultSet resultSet = dao.retrieve(query);
        List<OperationPolicy> operationPolicyList = new ArrayList<>();
        int i = 0;

        try {
            while (resultSet.next()){
                operationPolicyList.add(new OperationPolicy());
                operationPolicyList.get(i).setPolicyID(resultSet.getInt("policyID"));
                operationPolicyList.get(i).setName(resultSet.getString("name"));
                operationPolicyList.get(i).setContent(resultSet.getString("content"));
                i++;
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return operationPolicyList;
    }

    private OperationPolicy getOperationPolicy(ResultSet resultSet) throws SQLException {
        OperationPolicy operationPolicy = new OperationPolicy();
        operationPolicy.setPolicyID(resultSet.getInt("PolicyId"));
        operationPolicy.setName(resultSet.getString("name"));
        operationPolicy.setContent(resultSet.getString("content"));
        operationPolicy.setRating(resultSet.getInt("rating"));
        operationPolicy.setPass(resultSet.getInt("pass"));
        return operationPolicy;
    }
}
