package dao;

import contract.*;
import undewriting.AssumePolicy;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractDao implements ContractList {

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
        String query = "INSERT INTO Contract(contractDate, contractFile, customerID, insuranceID, specialization, contractState, contractRunState, contractUWState)"
            + " VALUES ('" + contract.getContractDate()
            + "', '" + contract.getContractFile()
            + "', " + contract.getCustomerID() + ", " + contract.getInsuranceID()
            + ", '" + contract.getSpecialization() + "', '" + contract.getContractState()
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
            ResultSet retrieve = dao.retrieve(query);
            if (retrieve.next()) {
                return getContract(retrieve);
            } else {
                return null;
            }
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
        String query = "UPDATE Contract SET "
                + "contractDate = '" + contract.getContractDate() + "', "
                + "contractFile = '" + contract.getContractFile() + "', "
                + "customerID = " + contract.getCustomerID() + ", "
                + "insuranceID = " + contract.getInsuranceID() + ", "
                + "specialization = '" + contract.getSpecialization() + "', "
                + "contractState = '" + contract.getContractState() + "', "
                + "contractRunState = '" + contract.getContractRunState() + "', "
                + "contractUWState = '" + contract.getContractUWState() + "' "
                + "WHERE contractID = " + contract.getContractID() + ";";
        dao.update(query);
    }

    public Contract getContract(ResultSet resultSet) throws SQLException {
        Contract contract = new Contract();
        contract.setContractID(resultSet.getInt("contractID"));
        Date contractDate = resultSet.getDate("contractDate");
        contract.setContractDate(dao.dateNullCheck(contractDate));
        contract.setContractFile(resultSet.getString("contractFile"));
        contract.setCustomerID(resultSet.getInt("customerID"));
        contract.setInsuranceID(resultSet.getInt("insuranceID"));
        contract.setSpecialization(resultSet.getString("specialization"));
        String contractState = resultSet.getString("contractState");
        // 여기는 2가지 방식 다 됨
        /*if(contractState.equals("OFFLINE")) {
            contract.setContractState(ContractState.OFFLINE);
        } else if(contractState.equals("ONLINE")) {
            contract.setContractState(ContractState.ONLINE);
        }*/
        contract.setContractState(dao.enumNullCheck(contractState, () -> ContractState.valueOf(contractState)));
        String contractRunState = resultSet.getString("contractRunState");
        /*if(contractRunState.equals("READY")) {
            contract.setContractRunState(ContractRunState.READY);
        } else if(contractRunState.equals("FINISH")) {
            contract.setContractRunState(ContractRunState.FINISH);
        }*/
        contract.setContractRunState(dao.enumNullCheck(contractRunState, () -> ContractRunState.valueOf(contractRunState)));
        String contractUWState = resultSet.getString("contractUWState");
        /*if(contractUWState.equals("BASIC")) {
            contract.setContractUWState(ContractUWState.BASIC);
        } else if(contractUWState.equals("COLLABORATIVE")) {
            contract.setContractUWState(ContractUWState.COLLABORATIVE);
        }*/
        contract.setContractUWState(dao.enumNullCheck(contractUWState, () -> ContractUWState.valueOf(contractUWState)));
        return contract;
    }

}
