package Teams;
import insurance.Insurance;
import util.Constants;
import util.Constants.Crud;
import util.Constants.Target;

public abstract class Team {
	private int teamID;
	private String teamName;
	public Insurance m_Insurance;

	public Team(){}
	public abstract void establishPolicy( Target target, Crud crud );
	public abstract void manage( Target target, Crud crud );
	public abstract void plan( Target target, Crud crud );
	public abstract void process( Target target, Crud crud );
	
	public int getTeamID() {return teamID;}
	public void setTeamID(int teamID) {this.teamID = teamID;}
	public String getTeamName() {return teamName;}
	public void setTeamName(String teamName) {this.teamName = teamName;}
	public void finalize() throws Throwable {}
}