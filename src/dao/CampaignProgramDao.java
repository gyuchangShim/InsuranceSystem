package dao;

import insurance.Insurance;
import marketingPlanning.CampaignProgram;
import marketingPlanning.CampaignProgramList;
import marketingPlanning.CampaignState;
import util.Constants;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampaignProgramDao implements CampaignProgramList {
    private Dao dao;

    public CampaignProgramDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(CampaignProgram campaignProgram) {
        String query = "INSERT INTO CampaignProgram (budget, campaignName, campaignTarget, duration, exResult, insuranceID, place, campaignWay, endResult, state, outTeam) VALUES ("
                + campaignProgram.getBudget() + ", '"
                + campaignProgram.getCampaignName() + "', '"
                + campaignProgram.getCampaignTarget() + "', "
                + campaignProgram.getDuration() + ", "
                + campaignProgram.getExResult() + ", "
                + campaignProgram.getInsuranceID() + ", '"
                + campaignProgram.getPlace() + "', '"
                + campaignProgram.getCampaignWay() + "', "
                + campaignProgram.getEndResult() + ", '"
                + campaignProgram.getProgramState() + "', '"
                + campaignProgram.getOutTeam() + "');";
        dao.create(query);
    }

    @Override
    public void delete(int campaignProgramID) {
        // create query
        String query = "DELETE FROM CampaignProgram WHERE campaingProgramID = " + campaignProgramID + ";";
        dao.delete(query);
    }

    @Override
    public void update(CampaignProgram campaignProgram) {
        String query = "UPDATE CampaignProgram SET budget = " + campaignProgram.getBudget() + ", "
            + "campaignName = '" + campaignProgram.getCampaignName() + "', "
            + "campaignTarget = '" + campaignProgram.getCampaignTarget() + "', "
            + "duration = " + campaignProgram.getDuration() + ", "
            + "exResult = " + campaignProgram.getExResult() + ", "
            + "insuranceID = " + campaignProgram.getInsuranceID() + ", "
            + "place = '" + campaignProgram.getPlace() + "', "
            + "campaignWay = '" + campaignProgram.getCampaignWay() + "', "
            + "endResult = " + campaignProgram.getEndResult() + ", "
            + "state = '" + campaignProgram.getProgramState() + "', "
            + "outTeam = '" + campaignProgram.getOutTeam() + "' "
            + "WHERE campaignID = " + campaignProgram.getCampaignID() + ";";
        dao.update(query);
    }

    @Override
    public List<CampaignProgram> retrieveAll() {
        String query = "SELECT * FROM CampaignProgram";
        try {
            ResultSet resultSet = dao.retrieve(query);
            List<CampaignProgram> campaignProgramList = new ArrayList<>();
            while(resultSet.next()) {
                campaignProgramList.add(getCampaignProgram(resultSet));
            }
            return campaignProgramList;
        }catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public CampaignProgram retrieve(int campaignProgramId) {
        String query = "SELECT * FROM CampaignProgram WHERE campaignID = " + campaignProgramId + ";";
        try {
            ResultSet resultSet = dao.retrieve(query);
            if (resultSet.next()) {
                return getCampaignProgram(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CampaignProgram getCampaignProgram(ResultSet resultSet) throws SQLException {
        CampaignProgram campaignProgram = new CampaignProgram();
        campaignProgram.setCampaignID(resultSet.getInt("campaignID"));
        campaignProgram.setBudget(resultSet.getInt("budget"));
        campaignProgram.setCampaignName(resultSet.getString("campaignName"));
        campaignProgram.setCampaignTarget(resultSet.getString("campaignTarget"));
        campaignProgram.setDuration(resultSet.getInt("duration"));
        campaignProgram.setExResult(resultSet.getFloat("exResult"));
        campaignProgram.setInsuranceID(resultSet.getInt("insuranceID"));
        campaignProgram.setPlace(resultSet.getString("place"));
        campaignProgram.setCampaignWay(resultSet.getString("campaignWay"));
        campaignProgram.setEndResult(resultSet.getFloat("endResult"));
        campaignProgram.setProgramState(CampaignState.valueOf(resultSet.getString("state")));
        campaignProgram.setOutTeam(resultSet.getString("outTeam"));
        return campaignProgram;
    }

}
