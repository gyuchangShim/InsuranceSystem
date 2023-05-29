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
        String query = "insert into OperationPolicyList (name ,content ) values('"
            + operationPolicy.getName() + "', '"
            + operationPolicy.getContent() +  "');";
        dao.create(query);
    }

    @Override
    public void delete(int operationPolicyId) {

    }

    @Override
    public OperationPolicy retrieve(int operationPolicyId) {
        return null;
    }

    @Override
    public void update(OperationPolicy operationPolicy) {

    }

    @Override
    public List<OperationPolicy> retrieveAll() {
        String query = "SELECT * from OperationPolicyList";
        ResultSet resultSet = dao.retrieve(query);
        List<OperationPolicy> operationPolicyList = new ArrayList<OperationPolicy>();
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
}
