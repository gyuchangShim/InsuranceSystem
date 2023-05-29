package dao;

import insurance.Insurance;
import insurance.InsuranceList;
import insurance.InsuranceState;
import insurance.InsuranceType;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsuranceDao implements InsuranceList {

    private Dao dao;

    public InsuranceDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Insurance insurance) {
        String query = "INSERT INTO Insurance (insuranceName, planReport, insuranceState, insuranceType, salesTarget, canRegistTarget, payment, guarantee, estimatedDevelopment, estimatedProfitRate, riskDegree, salesStartDate, salesEndDate, goalPeopleNumber, salesMethod, rate, duration, resultAnalysis, rewardAmount, salesPerformance) "
            + "VALUES ('" + insurance.getInsuranceName() + "', '"
            + insurance.getPlanReport() + "', '"
            + insurance.getInsuranceState() + "', '"
            + insurance.getInsuranceType() + "', '"
            + insurance.getSalesTarget() + "', '"
            + insurance.getCanRegistTarget() + "', "
            + insurance.getPayment() + ", '"
            + insurance.getGuarantee() + "', "
            + insurance.getEstimatedDevelopment() + ", "
            + insurance.getEstimatedProfitRate() + ", "
            + insurance.getRiskDegree() + ", "
            + insurance.getSalesStartDate() + ", "
            + insurance.getSalesEndDate() + ", "
            + insurance.getGoalPeopleNumber() + ", '"
            + insurance.getSalesMethod() + "', "
            + insurance.getRate() + ", "
            + insurance.getDuration() + ", "
            + insurance.getResultAnalysis() + ", "
            + insurance.getRewardAmount() + ", "
            + insurance.getSalesPerformance() + ");";
        dao.create(query);
    }

    @Override
    public void delete(int insuranceId) {
    	String query = "DELETE FROM Insurance WHERE insuranceID = " + insuranceId + ";";
        dao.delete(query);
    }

    @Override
    public Insurance retrieve(int insuranceId) {
        String query = "SELECT * FROM Insurance WHERE insuranceID = " + insuranceId + ";";
        try {
            return getInsurance(dao.retrieve(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Insurance> retrieveAll() {
        String query = "SELECT * FROM Insurance;";
        try {
            ResultSet resultSet = dao.retrieve(query);
            List<Insurance> insuranceList = new ArrayList<>();
            while(resultSet.next()) {
                insuranceList.add(getInsurance(resultSet));
            }
            return insuranceList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Insurance insurance) {
        String query = "UPDATE Insurance SET insuranceName = '" + insurance.getInsuranceName() + "', "
            + "planReport = '" + insurance.getPlanReport() + "', "
            + "insuranceState = '" + insurance.getInsuranceState() + "', "
            + "insuranceType = '" + insurance.getInsuranceType() + "', "
            + "salesTarget = '" + insurance.getSalesTarget() + "', "
            + "canRegistTarget = '" + insurance.getCanRegistTarget() + "', "
            + "payment = " + insurance.getPayment() + ", "
            + "guarantee = '" + insurance.getGuarantee() + "', "
            + "estimatedDevelopment = " + insurance.getEstimatedDevelopment() + ", "
            + "estimatedProfitRate = " + insurance.getEstimatedProfitRate() + ", "
            + "riskDegree = " + insurance.getRiskDegree() + ", "
            + "salesStartDate = " + insurance.getSalesStartDate() + ", "
            + "salesEndDate = " + insurance.getSalesEndDate() + ", "
            + "goalPeopleNumber = " + insurance.getGoalPeopleNumber() + ", "
            + "salesMethod = '" + insurance.getSalesMethod() + "', "
            + "rate = " + insurance.getRate() + ", "
            + "duration = " + insurance.getDuration() + ", "
            + "resultAnalysis = " + insurance.getResultAnalysis() + ", "
            + "rewardAmount = " + insurance.getRewardAmount() + ", "
            + "salesPerformance = " + insurance.getSalesPerformance() + " "
            + "WHERE insuranceID = " + insurance.getInsuranceID() + ";";
        dao.update(query);
    }

    private Insurance getInsurance(ResultSet resultSet) throws SQLException {
        Insurance insurance = new Insurance();
        insurance.setInsuranceID(resultSet.getInt("insuranceID"));
        insurance.setInsuranceName(resultSet.getString("insuranceName"));
        insurance.setPlanReport(resultSet.getString("planReport"));
        insurance.setInsuranceState(InsuranceState.valueOf(resultSet.getString("insuranceState")));
        String insuranceType = resultSet.getString("insuranceType");
        insurance.setInsuranceType(dao.enumNullCheck(insuranceType, () -> InsuranceType.valueOf(insuranceType)));
        insurance.setSalesTarget(resultSet.getString("salesTarget"));
        insurance.setCanRegistTarget(resultSet.getString("canRegistTarget"));
        insurance.setPayment(resultSet.getInt("payment"));
        insurance.setGuarantee(resultSet.getString("guarantee"));
        insurance.setEstimatedDevelopment(resultSet.getInt("estimatedDevelopment"));
        insurance.setEstimatedProfitRate(resultSet.getFloat("estimatedProfitRate"));
        insurance.setRiskDegree(resultSet.getInt("riskDegree"));
        Date salesStartDate = resultSet.getDate("salesStartDate");
        insurance.setSalesStartDate(dao.dateNullCheck(salesStartDate));
        Date salesEndDate = resultSet.getDate("salesEndDate");
        insurance.setSalesEndDate(dao.dateNullCheck(salesEndDate));
        insurance.setGoalPeopleNumber(resultSet.getInt("goalPeopleNumber"));
        insurance.setSalesMethod(resultSet.getString("salesMethod"));
        insurance.setRate(resultSet.getFloat("rate"));
        insurance.setDuration(resultSet.getInt("duration"));
        insurance.setResultAnalysis(resultSet.getInt("resultAnalysis"));
        insurance.setRewardAmount(resultSet.getInt("rewardAmount"));
        insurance.setSalesPerformance(resultSet.getInt("salesPerformance"));
        return insurance;
    }


}
