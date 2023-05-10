package Teams;
import insurance.Insurance;

public abstract class Team {

	private int teamID;
	private String teamName;
	public Insurance m_Insurance;

	public Team(){

	}
	public abstract void establishPolicy( int diff1, int diff2 );
	public abstract void manage( int diff1, int diff2 );
	public abstract void plan( int diff1, int diff2);
	public abstract void process( int diff1, int diff2 );
	
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public void finalize() throws Throwable {

	}

	

}