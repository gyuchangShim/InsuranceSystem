package marketingPlanning;

import insurance.Insurance;
import java.util.List;
import marketingPlanning.CampaignProgram;

public interface CampaignProgramList {
    void add(CampaignProgram campaignProgram);
    void delete(CampaignProgram campaignProgramId);
    CampaignProgram retrieve(int campaignProgramId);
    List<CampaignProgram> retrieveAll();
    void update(CampaignProgram campaignProgram);

}