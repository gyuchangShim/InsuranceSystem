package teams;

import marketingPlanning.CampaignProgram;
import marketingPlanning.CampaignProgramList;
import marketingPlanning.CampaignProgramListImpl;
import marketingPlanning.CampaignState;
import util.Constants.Target;
import util.Constants.Crud;

public class MarketingPlanningTeam extends Team {

	private CampaignProgramList campaignProgramList;

	public MarketingPlanningTeam(CampaignProgramList campaignProgramList){
		this.campaignProgramList = campaignProgramList;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void analyzeCampaign(){

	}

	@Override
	public void establishPolicy(Target target, Crud crud2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manage(Target target, Crud crud) {
		// TODO Auto-generated method stub

	}

	@Override
	public void plan(Target target, Crud crud) {
		if (target == Target.CAMPAIGN_PROGRAM) {
			if (crud == Crud.CREATE) {
				CampaignProgram campaignProgram = new CampaignProgram();
				String campaignPlan = util.TuiReader.readInput("캠페인 프로그램의 내용이 형식과 맞지 않습니다.");
				String[] campaignPlanSplit = campaignPlan.split("/");

				campaignProgram.setInsuranceID(Integer.valueOf(campaignPlanSplit[0]));
				campaignProgram.setCampaignName(campaignPlanSplit[1]);
				campaignProgram.setCampaignTarget(campaignPlanSplit[2]);
				campaignProgram.setDuration(Integer.valueOf(campaignPlanSplit[3]));
				campaignProgram.setCampaignWay(campaignPlanSplit[4]);
				campaignProgram.setBudget(Integer.valueOf((campaignPlanSplit[5])));
				campaignProgram.setExResult(Integer.valueOf(campaignPlanSplit[6]));
				campaignProgram.setProgramState(CampaignState.Plan);
				campaignProgramList.add(campaignProgram);
			}
		}
	}

	@Override
	public void process(Target target, Crud crud) {
		// TODO Auto-generated method stub

	}

}