package marketingPlanning;

import insurance.Insurance;
import java.util.ArrayList;
import java.util.List;

public class CampaignProgramListImpl implements CampaignProgramList {

	private List<CampaignProgram> campaignProgramList;

	public CampaignProgramListImpl(){
		campaignProgramList = new ArrayList<>();
	}

	@Override
	public void add(CampaignProgram campaignProgram) {

	}

	@Override
	public void delete(int campaignProgramId) {

	}

	@Override
	public CampaignProgram retrieve(int campaignProgramId) {
		// 임시로 만듦
		return new CampaignProgram();
	}

	@Override
	public List<CampaignProgram> retrieveAll() {
		return null;
	}

	@Override
	public void update(CampaignProgram campaignProgram) {

	}
}