package teams;

import exception.CIllegalArgumentException;
import marketingPlanning.CampaignProgram;
import marketingPlanning.CampaignProgramList;
import marketingPlanning.CampaignProgramListImpl;
import marketingPlanning.CampaignState;
import util.Constants.Target;
import util.Constants.Crud;

public class MarketingPlanningTeam extends Team {

    private CampaignProgramList campaignProgramList;

    public MarketingPlanningTeam(CampaignProgramList campaignProgramList) {
        this.campaignProgramList = campaignProgramList;
    }

    public void finalize() throws Throwable {
        super.finalize();
    }

    public void analyzeCampaign() {

    }

    @Override
    public void establishPolicy(Target target, Crud crud2) {

    }

    @Override
    public void manage(Target target, Crud crud) {

    }

    @Override
    public void plan(Target target, Crud crud) {

    }

    @Override
    public void process(Target target, Crud crud) {

    }

}