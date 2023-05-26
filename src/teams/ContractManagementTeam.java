package teams;
import java.util.Vector;

import contract.Contract;
import contract.ContractListImpl;
import contract.ContractModify;
import contract.ContractModifyListImpl;
import contractManagement.ContractManagementPolicy;
import contractManagement.ContractManagementPolicyListImpl;
import customer.Customer;
import customer.CustomerListImpl;
import insurance.Insurance;
import insurance.InsuranceListImpl;
import util.Constants.Crud;
import util.Constants.Target;

public class ContractManagementTeam extends Team {
	public ContractManagementPolicy m_ContractManagementPolicy;
	public ContractModify m_contractModify;
	public Contract m_Contract;
	private ContractModifyListImpl contractModifyListImpl;
	private ContractListImpl contractListImpl;
	private InsuranceListImpl insuranceListImpl;
	private CustomerListImpl customerListImpl;
	private ContractManagementPolicyListImpl policyListImpl;

	public ContractManagementTeam(){
		this.contractModifyListImpl = new ContractModifyListImpl();
		this.contractListImpl = new ContractListImpl();
		this.insuranceListImpl = new InsuranceListImpl();
		this.customerListImpl = new CustomerListImpl();
		this.policyListImpl = new ContractManagementPolicyListImpl();
	}
	public Vector<ContractModify> getAllContractModify(){
		//모든 배서 신청 목록을 가져온다.
		return this.contractModifyListImpl.retrieve("");
	}
	public Vector<ContractModify> getProcessContractModifyList(){
		// 신청 결과 대기 배서 신청 목록을 가져온다.
		return this.contractModifyListImpl.retrieve("");
	}
	public Customer getCustomerInformation( int customerID ) {
		return null;		// 확인 필요
	}
	public Contract getContract( int contractID ) {
		return this.contractListImpl.retrieve( "" ).get(0);
	}
	public Insurance getInsurance( int insuranceID ) {
		return this.insuranceListImpl.retrieve(insuranceID);
	}
	public void setContractModify( ContractModify contractModify ) {
		this.m_contractModify = contractModify;
	}
	public void setContract( Contract contract ) {
		this.m_Contract = contract;
	}
	public void setPolicy( ContractManagementPolicy policy ) {
		this.m_ContractManagementPolicy = policy;
	}
	public Vector<Insurance> getInsuranceByCustomerID( int customerID ){
		return this.insuranceListImpl.getInsuranceByCustomerID(customerID);
	}
	public Vector<Contract> getContractByInsuranceAndCustomerID( int insuranceID, int customerID ) {
		String query = "";
		return this.contractListImpl.retrieve(query);
	}
	public Vector<ContractManagementPolicy> getAllPolicy(){
		return this.policyListImpl.retrieve("");
	}
	public Vector<Insurance> getAllInsurance(){
		return (Vector<Insurance>) this.insuranceListImpl.retrieveAll();
	}
	public Vector<Contract> getContractByInsuranceID( int insuranceID ){
		// 해당 보험에 관련된 모든 계약을 가져온다.
		return this.contractListImpl.retrieve("");
	}

	@Override
	public void establishPolicy(Target target, Crud crud) {}
	@Override
	public void manage(Target target, Crud crud) {
		switch( target ) {
		case CONTRACT_MODIFY:
			switch( crud ) {
			case CREATE:
				this.contractModifyListImpl.add( this.m_contractModify );
				break;
			case READ:
				break;
			case UPDATE:
				this.contractModifyListImpl.update( this.m_contractModify );
				break;
			case DELETE:
				break;
			}
			break;
		case CONTRACT:
			switch( crud ) {
			case CREATE:
				break;
			case READ:
				break;
			case UPDATE:
				this.contractListImpl.update( this.m_Contract );
				break;
			case DELETE:
				break;
			}
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