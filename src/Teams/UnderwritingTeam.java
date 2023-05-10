package Teams;
import Teams.Team;
import Undewriting.AssumePolicy;

public class UnderwritingTeam extends Team {

	public AssumePolicy m_AssumePolicy;

	public UnderwritingTeam(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void examine(){

	}

	@Override
	public void establishPolicy(int diff1, int diff2) {
		
	}

	@Override
	public void manage(int diff1, int diff2) {
		
	}

	@Override
	public void plan(int diff1, int diff2) {
		
	}

	@Override
	public void process(int diff1, int diff2) {
		
	}


}