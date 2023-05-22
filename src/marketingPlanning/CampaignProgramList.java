package MarketingPlanning;

import insurance.Insurance;
import java.util.List;

public interface CampaignProgramList {
    void add(CampaignProgram campaignProgram);
    void delete(int campaignProgramId);
    CampaignProgram retrieve(int campaignProgramId);
    List<Insurance> retrieveAll();
    void update(CampaignProgram campaignProgram);

}