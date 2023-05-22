package Dao;

import MarketingPlanning.CampaignProgram;
import MarketingPlanning.CampaignProgramList;
import insurance.Insurance;
import insurance.InsuranceList;

public class CampaignProgramDao extends Dao{

    public CampaignProgramDao() {
        try {
            super.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void create(Insurance insurance) {
        // create query
        String query = "insert into insurance values ("
                + insurance.getInsuranceID() + ", "
                + insurance.getInsuranceName() + ");";
        System.out.println(query);
    }

    public CampaignProgram retrieveAll() {
        String query = "";
        CampaignProgramList campaignProgramList = new CampaignProgramList();
        return null;
    }

    public CampaignProgram retrieveById(int campaignProgramId) {
        return null;
    }

    public void update(CampaignProgram campaignProgram) {
        // create query
        String query = "update into campaign values ("
                + campaignProgram.getCampaignID() + ", "
                + campaignProgram.getCampaignName() + ");";
        System.out.println(query);
    }

    public void delete(CampaignProgram campaignProgram) {
        // create query
        String query = "delete from campaign values ("
                + campaignProgram.getCampaignID() + ", "
                + campaignProgram.getCampaignName() + ");";
        System.out.println(query);
    }


}
