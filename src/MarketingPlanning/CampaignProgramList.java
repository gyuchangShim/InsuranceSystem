package MarketingPlanning;

import java.util.ArrayList;
import java.util.List;

public interface CampaignProgramList {

    List<CampaignProgram> retrieveRun();

    List<CampaignProgram> retrieveAll();

    ArrayList<CampaignProgram> retrieveAllEnd();
}