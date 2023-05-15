package Teams;

import java.util.Vector;

import Reward.Reward;
import Reward.RewardListImpl;
import util.Constants;

public class RewardTeam extends Team {
	private Reward reward;
	private Vector<Reward> rewardList;
	
	private RewardListImpl rewardListImpl;

	public RewardTeam(){
		this.rewardList = new Vector<Reward>();
		this.rewardListImpl = new RewardListImpl();
	}

	public void finalize() throws Throwable {super.finalize();}
	public Vector<Reward> getAllReward(){
		// RewardListImpl에서 모든 엔티티를 가져온 다음 리턴 
		return this.rewardList;
	}
	public void rewardResult( int id, Constants.Result result ) {
		// Reward getReward = this.rewardListImpl.getReward( id );
		// getReward.setAppliResult( result );
		// this.rewardListImpl.update( getReward );
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