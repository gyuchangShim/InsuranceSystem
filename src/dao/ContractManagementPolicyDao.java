package dao;

import exception.CustomException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import contractManagement.ContractManagementPolicy;
import contractManagement.ContractManagementPolicyList;

public class ContractManagementPolicyDao implements ContractManagementPolicyList {
	private Dao dao;

    public ContractManagementPolicyDao	() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
			throw new CustomException(e);
        }
    }
	@Override
	public List<ContractManagementPolicy> retrieveAll() {
		String query = "SELECT * from ContractManagementPolicy;";
        ResultSet resultSet = dao.retrieve(query);
        List<ContractManagementPolicy> policyList = new ArrayList<ContractManagementPolicy>();
        try {
            while (resultSet.next()){
            	ContractManagementPolicy policy = new ContractManagementPolicy();
            	policy.setPolicyID( resultSet.getInt("policyID") );
            	policy.setName( resultSet.getString( "name" ) );
            	policy.setContent( resultSet.getString( "content" ) );
            	policyList.add( policy );
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return policyList;
	}

	@Override
	public void add(ContractManagementPolicy contractManagementPolicy) {
		String query = "INSERT into ContractManagementPolicy (content, name) VALUES('"
	            + contractManagementPolicy.getContent() +  "', '"
	            + contractManagementPolicy.getName() + "'); ";
	    dao.create(query);		
	}

	@Override
	public void delete(int contractManagementPolicyID) {
		String query = "DELETE FROM ContractManagementPolicy WHERE policyID = " + contractManagementPolicyID + ";";
        dao.delete(query);		
	}

	@Override
	public ContractManagementPolicy retrieve(int contractManagementPolicyID) {
		String query = "SELECT * FROM ContractManagementPolicy WHERE policyID=" + contractManagementPolicyID + ";";
		ResultSet resultSet = dao.retrieve( query );
		ContractManagementPolicy policy = new ContractManagementPolicy();
		try {
			policy.setPolicyID( resultSet.getInt( "policyID" ) );
			policy.setName( resultSet.getString( "name" ) );
			policy.setContent( resultSet.getString("content") );
		} catch (SQLException e) {throw new RuntimeException(e);}
		return policy;		
	}

	@Override
	public void update(ContractManagementPolicy policy) {
		String query = "UPDATE ContractManagementPolicy SET name = '" + policy.getName() + "', "
				+ "content = '" +  policy.getContent() + "' "
				+ "WHERE policyID = " + policy.getPolicyID() + ";";
		dao.update( query );		
	}

}
