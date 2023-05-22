package Teams;
import MarketingPlanning.CampaignProgram;
import MarketingPlanning.CampaignProgramList;
import MarketingPlanning.CampaignProgramListImpl;
import Teams.Team;
import Undewriting.AssumePolicy;
import util.Constants.Target;
import util.Constants.Crud;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MarketingPlanningTeam extends Team {

	public CampaignProgram campaignProgram;
	public CampaignProgramListImpl campaignProgramList;


	public MarketingPlanningTeam(){
		this.campaignProgram = new CampaignProgram();
		this.campaignProgramList = new CampaignProgramListImpl();
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
				String campaignPlan = util.TuiReader.readInput("캠페인 프로그램의 내용이 형식과 맞지 않습니다.");
				String[] campaignPlanSplit = campaignPlan.split("/");
				campaignProgram.setInsuranceID(Integer.valueOf(campaignPlanSplit[0]));
				campaignProgram.setCampaignTarget(campaignPlanSplit[1]);
				campaignProgram.setDuration(Integer.valueOf(campaignPlanSplit[2]));
				campaignProgram.setCampaignWay(campaignPlanSplit[3]);
				campaignProgram.setBudget(Integer.valueOf((campaignPlanSplit[4])));
				campaignProgram.setExResult(Integer.valueOf(campaignPlanSplit[5]));
				campaignProgram.setProgramState(CampaignProgram.campaignState.Plan);
				campaignProgramList.add(campaignProgram);
			}
		}
	}

	@Override
	public void process(Target target, Crud crud) {
		// TODO Auto-generated method stub
		
	}

}