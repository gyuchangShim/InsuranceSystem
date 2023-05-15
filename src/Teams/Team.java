package Teams;
import insurance.Insurance;
import util.Constants;

public abstract class Team {
	private int teamID;
	private String teamName;
	public Insurance m_Insurance;

	public Team(){}
	public abstract void establishPolicy( Constants.Target target, int crud );
	public abstract void manage( Constants.Target target, int crud );
	public abstract void plan( Constants.Target target, int crud );
	public abstract void process( Constants.Target target, int crud );
	
	public int getTeamID() {return teamID;}
	public void setTeamID(int teamID) {this.teamID = teamID;}
	public String getTeamName() {return teamName;}
	public void setTeamName(String teamName) {this.teamName = teamName;}
	public void finalize() throws Throwable {}
}