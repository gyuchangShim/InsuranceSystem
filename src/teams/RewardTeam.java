package teams;

import java.util.ArrayList;
import java.util.List;

import contract.Contract;
import contract.ContractList;
import customer.Customer;
import customer.CustomerList;
import insurance.Insurance;
import insurance.InsuranceList;
import reward.Reward;
import reward.RewardList;
import util.Constants.Crud;
import util.Constants.Result;
import util.Constants.Target;

public class RewardTeam extends Team {
	private Reward reward;
	private RewardList rewardListImpl;
	private ContractList contractListImpl;
	private CustomerList customerListImpl;
	private InsuranceList insuranceListImpl;
	
	public RewardTeam( RewardList rewardList, ContractList contractList, CustomerList customerList, InsuranceList insuranceList ){
		this.rewardListImpl = rewardList;
		this.contractListImpl = contractList;
		this.customerListImpl = customerList;
		this.insuranceListImpl = insuranceList;
	}
	public List<Reward> getAllReward(){
		return this.rewardListImpl.retrieveAll();
	}
	public void rewardResult( int rewardID, Result result ) {
		List<Reward> rewardList = this.rewardListImpl.retrieveAll();
		Reward selectedReward = null;
		for( Reward reward : rewardList ) {
			if( reward.getRewardID()==rewardID ) {
				selectedReward = reward;
				selectedReward.setAppliResult( result );
				break;
			}
		}
		if( selectedReward != null ) {
			this.rewardListImpl.update( selectedReward );
		}
	}
	public List<Insurance> getCustomerInsurance( int customerID ){
		List<Contract> contractList = this.contractListImpl.retrieveAll();
		List<Integer> insuranceIdList = new ArrayList<Integer>();
		List<Insurance> resultInsuranceList = new ArrayList<Insurance>();
		for( Contract contract : contractList ) {
			if( contract.getCustomerID()==customerID ) {
				insuranceIdList.add( contract.getInsuranceID() );
			}
		}
		for( Integer i : insuranceIdList ) {
			resultInsuranceList.add( this.insuranceListImpl.retrieve( i ) );
		}
		return resultInsuranceList;
	}
	public List<Contract> getCustomerContract( int customerID ){
		List<Contract> contractList = this.contractListImpl.retrieveAll();
		List<Contract> resultContractList = new ArrayList<Contract>();
		for( Contract contract : contractList ) {
			if( contract.getCustomerID()==customerID ) {
				resultContractList.add( contract );
			}
		}
		return resultContractList;
	}
	public Customer getCustomerInformation( int customerID ) {
		return this.customerListImpl.retrieve(customerID);
	}
	public void setReward( Reward reward ) {
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