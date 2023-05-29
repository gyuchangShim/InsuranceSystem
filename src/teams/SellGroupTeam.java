package teams;

import business.SellGroup;
import business.SellGroupList;
import customer.Customer;
import insurance.Insurance;
import insurance.InsuranceList;
import insurance.InsuranceState;
import java.util.Random;
import java.util.stream.Collectors;
import util.Constants.Crud;
import util.Constants.Target;

import java.util.List;

public class SellGroupTeam extends Team {

	private SellGroupList sellGroupList;
	private InsuranceList insuranceList;

	public SellGroupTeam(SellGroupList sellGroupList, InsuranceList insuranceList){
		this.sellGroupList = sellGroupList;
		this.insuranceList = insuranceList;
	}

	public void concludeContract(){

	}

	public void reportConsultingResult(){

	}

	@Override
	public void establishPolicy(Target target, Crud crud) {

	}

	@Override
	public void manage(Target target, Crud crud) {

	}

	@Override
	public void plan(Target target, Crud crud) {

	}

	@Override
	public void process(Target target, Crud crud) {

	}
	public List<SellGroup> getAllGroup() {
		return sellGroupList.retrieveAll();
	}
	public List<Insurance> recommendInsurance(Customer customer) {
		return insuranceList.retrieveAll()
			.stream()
			.filter(insurance -> insurance.getInsuranceState() == InsuranceState.AUTHORIZED)
			.limit(5)
			.collect(Collectors.toList());
	}
	public int calculateInsuranceFee(Insurance insurance, Customer customer) {
		return new Random().nextInt(10000, 20000);
	}

	public String recommendInsuranceReason(Insurance insurance, Customer customer) {
		return "~~~ 이유로 이 보험을 추천합니다.";
	}
}
