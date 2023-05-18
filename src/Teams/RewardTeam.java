package Teams;

import java.util.Vector;

import Reward.Reward;
import Reward.RewardListImpl;
import util.Constants;
import util.Constants.Crud;
import util.Constants.Target;

public class RewardTeam extends Team {
	private Reward reward;
	private RewardListImpl rewardListImpl;
	private Vector<Reward> rewardList;	
	
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
	public void establishPolicy(Target target, Crud crud) {}
	@Override
	public void manage(Target target, Crud crud) {}
	@Override
	public void plan(Target target, Crud crud) {}
	@Override
	public void process(Target target, Crud crud) {}
}