package dao;

import undewriting.AssumePolicy;
import undewriting.AssumePolicyList;
import undewriting.PolicyType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssumePolicyDao implements AssumePolicyList {

    private Dao dao;

    public AssumePolicyDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(AssumePolicy assumePolicy) {
        String query = "INSERT INTO AssumePolicy (name, content, policyType) VALUES ('"
                + assumePolicy.getName() + "', '"
                + assumePolicy.getContent() + "', '" + assumePolicy.getPolicyType() + "');";
        dao.create(query);
    }

    @Override
    public void delete(int assumePolicyId) {
        String query = "DELETE FROM AssumePolicy WHERE policyID =" + assumePolicyId + ";";
        dao.delete(query);
    }

    public AssumePolicy retreive(int policyId) {
        try {
            String query = "SELECT * FROM AssumePolicy WHERE policyID = " + policyId + ";";
            return getAssumePolicy(dao.retrieve(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<AssumePolicy> retreiveAll() {
        try {
            // 조회 목록 선택 SQL문
            String query = "SELECT * FROM AssumePolicy";
            ResultSet resultSet = dao.retrieve(query);
            // SQL문으로 받아온 데이터 리스트로 정리
            List<AssumePolicy> assumePolicyList = new ArrayList<>();
            while(resultSet.next()) {
                assumePolicyList.add(getAssumePolicy(resultSet));
            }
            return assumePolicyList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(AssumePolicy assumePolicy) {
        String query = "UPDATE AssumePolicy SET "
                + "name = '" + assumePolicy.getName() + "', "
                + "content = '" + assumePolicy.getContent() + "', "
                + "policyType = '" + assumePolicy.getPolicyType() + "', "
                + "WHERE policyID = " + assumePolicy.getPolicyID() + ";";
        dao.update(query);
    }


    public AssumePolicy getAssumePolicy(ResultSet resultSet) throws SQLException {
        AssumePolicy assumePolicy = new AssumePolicy();
        assumePolicy.setPolicyID(resultSet.getInt("policyID"));
        assumePolicy.setName(resultSet.getString("name"));
        assumePolicy.setContent(resultSet.getString("content"));
        assumePolicy.setPolicyType(PolicyType.valueOf(resultSet.getString("policyType")));
        return assumePolicy;
    }
}
