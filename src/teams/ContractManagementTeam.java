package teams;
import java.util.Vector;

import contract.AdviceNote;
import contract.AdviceNoteListImpl;
import contract.Contract;
import contract.ContractListImpl;
import contract.Payment;
import contract.PaymentListImpl;
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
	public Contract m_Contract;
	public Payment m_Payment;
	public AdviceNote m_AdviceNote;
	private ContractListImpl contractListImpl;
	private InsuranceListImpl insuranceListImpl;
	private CustomerListImpl customerListImpl;
	private ContractManagementPolicyListImpl policyListImpl;
	private PaymentListImpl paymentListImpl;
	private AdviceNoteListImpl adviceNoteListImpl;	

	public ContractManagementTeam(){
		this.contractListImpl = new ContractListImpl();
		this.insuranceListImpl = new InsuranceListImpl();
		this.customerListImpl = new CustomerListImpl();
		this.policyListImpl = new ContractManagementPolicyListImpl();
		this.paymentListImpl = new PaymentListImpl();
		this.adviceNoteListImpl = new AdviceNoteListImpl();
	}
	public Customer getCustomerInformation( int customerID ) {
		return this.customerListImpl.retrieve(null);		// 확인 필요
	}
	public Contract getContract( int contractID ) {
		return this.contractListImpl.retrieve( contractID );
	}
	public Insurance getInsurance( int insuranceID ) {
		return this.insuranceListImpl.retrieve(insuranceID);
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
	public Vector<AdviceNote> getAllAdviceNote() {
		return this.adviceNoteListImpl.retrieveAll();
	}
	public Vector<Insurance> getInsuranceByCustomerID( int customerID ){
		return this.insuranceListImpl.getInsuranceByCustomerID(customerID);
	}
	public Vector<Contract> getContractByInsuranceAndCustomerID( int insuranceID, int customerID ) {
		Vector<Contract> contractList = this.contractListImpl.retrieveAll();
		Vector<Contract> result = new Vector<Contract>();
		for( Contract contract : contractList ) {
			if( contract.getInsuranceID()==insuranceID && contract.getCustomerID()==customerID ) result.add( contract );
		}
		return result;
	}
	public Vector<ContractManagementPolicy> getAllPolicy(){
		return this.policyListImpl.retrieveAll();
	}
	public Vector<Insurance> getAllInsurance(){
		return (Vector<Insurance>) this.insuranceListImpl.retrieveAll();
	}
	public Vector<Contract> getContractByInsuranceID( int insuranceID ){
		Vector<Contract> contractList = this.contractListImpl.retrieveAll();
		Vector<Contract> result = new Vector<Contract>();
		for( Contract contract : contractList ) {
			if( contract.getInsuranceID()==insuranceID ) result.add( contract );
		}
		return result;
	}
	public Vector<Payment> getAllPayment(){
		return this.paymentListImpl.retrieveAll();
	}
	public Vector<Contract> getAllContract(){
		return this.contractListImpl.retrieveAll();
	}

	@Override
	public void establishPolicy(Target target, Crud crud) {}
	@Override
	public void manage(Target target, Crud crud) {
		switch( target ) {
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
		case ADVICE_NOTE:
			switch( crud ) {
			case CREATE:
				this.adviceNoteListImpl.add( this.m_AdviceNote );
				break;
			case READ:
				break;
			case UPDATE:
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