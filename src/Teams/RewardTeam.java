package Teams;

import java.util.Vector;

import Reward.Reward;

public class RewardTeam extends Team {
	private Reward reward;
	private Vector<Reward> rewardList;

	public RewardTeam(){
		this.rewardList = new Vector<Reward>();
	}

	public void finalize() throws Throwable {super.finalize();}
	public Vector<Reward> getAllReward(){
		return this.rewardList;
	}
	@Override
	public void establishPolicy(int diff1, int diff2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manage(int diff1, int diff2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void plan(int diff1, int diff2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(int diff1, int diff2) {
		// TODO Auto-generated method stub
		
	}

}