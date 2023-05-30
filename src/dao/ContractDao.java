package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import contract.Contract;
import contract.ContractList;
import contract.ContractRunState;
import contract.ContractState;
import contract.ContractUWState;

public class ContractDao implements ContractList{
	private Dao dao;

    public ContractDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void add(Contract contract) {
        String query = "INSERT INTO Contract (contractID, contractDate, contractFile, customerID, insuranceID, specialization, contractState, contractRunState, contractUWState"
            + "VALUES ('" + contract.getContractID() + "', '" + contract.getContractDate()
            + "', '" + contract.getContractDate() + "', '" + contract.getContractFile()
                + "', '" + contract.getCustomerID() + "', '" + contract.getInsuranceID()
                + "', '" + contract.getSpecialization() + "', '" + contract.getContractState()
                + "', '" + contract.getContractRunState() + "', '" + contract.getContractUWState() + "');";
        dao.create(query);
    }

    @Override
    public void delete(int contractId) {
        String query = "DELETE FROM Contract WHERE contractID = " + contractId + ";";
        dao.delete(query);
    }

    @Override
    public Contract retrieve(int contractId) {
        try {
            String query = "SELECT * FROM Contract WHERE contractID = " + contractId + ";";
            return getContract(dao.retrieve(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contract> retrieveAll() {
        try {
            // 조회 목록 SQL 문
            String query = "SELECT * FROM Contract";
            ResultSet resultset = dao.retrieve(query);
            // SQL문으로 받아온 데이터 리스트로 정리
            List<Contract> contractList = new ArrayList<>();
            while(resultset.next()) {
                contractList.add(getContract(resultset));
            }
            return contractList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Contract contract) {
        String query = "UPDATE Contract SET contractId = '" + contract.getContractID() + "', "
                + "contractDate = '" + contract.getContractDate() + "', "
                + "contractFile = '" + contract.getContractFile() + "', "
                + "customerId = '" + contract.getCustomerID() + "', "
                + "insuranceId = '" + contract.getInsuranceID() + "', "
                + "specialization = '" + contract.getSpecialization() + "', "
                + "contractState = " + contract.getContractState() + ", "
                + "contractRunState = '" + contract.getContractRunState() + "', "
                + "contractUWState = '" + contract.getContractRunState() + "',"
                + "WHERE contractId = " + contract.getContractID() + ";";
        dao.update(query);
    }

    public Contract getContract(ResultSet resultSet) throws SQLException {
        Contract contract = new Contract();
        contract.setContractID(resultSet.getInt("contractID"));
        Date contractDate = resultSet.getDate("contractDate");
        contract.setContractDate(dao.dateNullCheck(contractDate));
        contract.setContractFile(resultSet.getString("contractFile"));
        contract.setCustomerID(resultSet.getInt("customerId"));
        contract.setInsuranceID(resultSet.getInt("insuranceId"));
        contract.setSpecialization(resultSet.getString("specialization"));
        contract.setContractState(ContractState.valueOf(resultSet.getString("contractState")));
        contract.setContractRunState(ContractRunState.valueOf(resultSet.getString("contractRunState")));
        contract.setContractUWState(ContractUWState.valueOf(resultSet.getString("contractUWState")));
        return contract;
    }
}
