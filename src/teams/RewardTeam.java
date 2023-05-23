package teams;

import contract.Contract;
import contract.ContractListImpl;
import customer.Customer;
import customer.CustomerListImpl;
import java.util.Vector;

import insurance.Insurance;
import reward.Reward;
import reward.RewardListImpl;
import util.Constants.Crud;
import util.Constants.Result;
import util.Constants.Target;

public class RewardTeam extends Team {
	private Reward reward;
	private RewardListImpl rewardListImpl;
	private Vector<Reward> rewardList;	
	private ContractListImpl contractListImpl;
	private CustomerListImpl customerListImpl;
	
	public RewardTeam(){
		this.rewardList = new Vector<Reward>();
		this.rewardListImpl = new RewardListImpl();
		this.contractListImpl = new ContractListImpl();
	}
	public void finalize() throws Throwable {}
	public Vector<Reward> getAllReward(){
		// RewardListImpl에서 모든 엔티티를 가져온 다음 리턴 
		return this.rewardList;
	}
	public void rewardResult( int id, Result result ) {
		// Reward getReward = this.rewardListImpl.getReward( id );
		// getReward.setAppliResult( result );
		// this.rewardListImpl.update( getReward );
	}
	public Vector<Insurance> getCustomerInsurance( int customerID ){
		// 해당 고객이 가입되어 있는 모든 보험 목록
		return null;
	}
	public Vector<Contract> getCustomerContract( int customerID ){
		// 해당 고객과 체결되어 있는 모든 계약 목록
		return null;
	}
	public Customer getCustomerInformation( int customerID ) {
		// 해당 고객의 정보 가져오기
		return null;
	}
	public void setReward( Reward reward ) {
		// reward 값을 입력 받는 함수
		this.reward = reward;
	}
	@Override
	public void establishPolicy(Target target, Crud crud) {}
	@Override
	public void manage(Target target, Crud crud) {
		switch( crud ) {
		case CREATE:
			break;
		case READ:
			break;
		case UPDATE:
			this.rewardListImpl.update( this.reward );
			break;
		case DELETE:
			break;
		default:
			break;
		}
	}
	@Override
	public void plan(Target target, Crud crud) {}
	@Override
	public void process(Target target, Crud crud) {}
}