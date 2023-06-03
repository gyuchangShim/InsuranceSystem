package teams;
import java.util.ArrayList;
import java.util.List;

import contract.AdviceNote;
import contract.AdviceNoteList;
import contract.Contract;
import contract.ContractList;
import contract.Payment;
import contract.PaymentList;
import contractManagement.ContractManagementPolicy;
import contractManagement.ContractManagementPolicyList;
import customer.Customer;
import customer.CustomerList;
import insurance.Insurance;
import insurance.InsuranceList;
import util.Constants.Crud;
import util.Constants.Target;

public class ContractManagementTeam extends Team {
	public ContractManagementPolicy m_ContractManagementPolicy;
	public Contract m_Contract;
	public Payment m_Payment;
	public AdviceNote m_AdviceNote;
	private ContractList contractList;
	private InsuranceList insuranceList;
	private CustomerList customerList;
	private ContractManagementPolicyList policyList;
	private PaymentList paymentList;
	private AdviceNoteList adviceNoteList;	

	public ContractManagementTeam(ContractList contractList, InsuranceList insuranceList, CustomerList customerList, ContractManagementPolicyList policyList, PaymentList paymentList, AdviceNoteList adviceNoteList){
		this.contractList = contractList;
		this.insuranceList = insuranceList;
		this.customerList = customerList;
		this.policyList = policyList;
		this.paymentList = paymentList;
		this.adviceNoteList = adviceNoteList;
	}
	public Customer getCustomerInformation( int customerID ) {
		return this.customerList.retrieve( customerID );		// 확인 필요
	}
	public Contract getContract( int contractID ) {
		return this.contractList.retrieve( contractID );
	}
	public Insurance getInsurance( int insuranceID ) {
		return this.insuranceList.retrieve(insuranceID);
	}
	public void setContract( Contract contract ) {
		this.m_Contract = contract;
	}
	public void setPolicy( ContractManagementPolicy policy ) {
		this.m_ContractManagementPolicy = policy;
	}
	public void setPayment( Payment payment ) {
		this.m_Payment = payment;
	}
	public void setAdviceNote( AdviceNote adviceNote ) {
		this.m_AdviceNote = adviceNote;
	}
	public List<AdviceNote> getAllAdviceNote() {
		return this.adviceNoteList.retrieveAll();
	}
	public Contract getContractByInsuranceAndCustomerID( int insuranceID, int customerID ) {
		List<Contract> contractList = this.contractList.retrieveAll();
		for( Contract contract : contractList ) {
			if( contract.getInsuranceID()==insuranceID && contract.getCustomerID()==customerID ) return contract;
		}
		return null;
	}
	public List<ContractManagementPolicy> getAllPolicy(){
		return this.policyList.retrieveAll();
	}
	public List<Insurance> getAllInsurance(){
		return this.insuranceList.retrieveAll();
	}
	public List<Contract> getContractByInsuranceID( int insuranceID ){
		List<Contract> contractList = this.contractList.retrieveAll();
		List<Contract> result = new ArrayList<Contract>();
		for( Contract contract : contractList ) {
			if( contract.getInsuranceID()==insuranceID ) result.add( contract );
		}
		return result;
	}
	public List<Payment> getAllPayment(){
		return this.paymentList.retrieveAll();
	}
	public List<Contract> getAllContract(){
		return this.contractList.retrieveAll();
	}

	@Override
	public void establishPolicy(Target target, Crud crud) {}
	@Override
	public void manage(Target target, Crud crud) {
		switch( target ) {
		case CONTRACT:
			switch( crud ) {
			case CREATE:
				this.contractList.add( this.m_Contract );
				break;
			case READ:
				this.contractList.retrieveAll();
				break;
			case UPDATE:
				this.contractList.update( this.m_Contract );
				break;
			case DELETE:
				this.contractList.delete( this.m_Contract.getContractID() );
				break;
			}
			break;
		case ADVICE_NOTE:
			switch( crud ) {
			case CREATE:
				this.adviceNoteList.add( this.m_AdviceNote );
				break;
			case READ:
				this.adviceNoteList.retrieveAll();
				break;
			case UPDATE:
				this.adviceNoteList.update( this.m_AdviceNote );
				break;
			case DELETE:
				this.adviceNoteList.delete( this.m_AdviceNote.getAdviceNoteID() );
				break;
			}
			break;
		case PAYMENT:
			switch( crud ) {
			case CREATE:
				this.paymentList.add( this.m_Payment );
				break;
			case READ:
				this.paymentList.retrieveAll();
				break;
			case UPDATE:
				this.paymentList.update( this.m_Payment );
				break;
			case DELETE:
				this.paymentList.delete( this.m_Payment.getPaymentID() );
				break;
			}
			break;
		case CONTRACT_MANAGEMENT_POLICY:
			switch( crud ) {
			case CREATE:
				this.policyList.add( this.m_ContractManagementPolicy );
				break;
			case READ:
				this.policyList.retrieveAll();
				break;
			case UPDATE:
				this.policyList.update( this.m_ContractManagementPolicy );
				break;
			case DELETE:
				this.policyList.delete( this.m_ContractManagementPolicy.getPolicyID() );
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