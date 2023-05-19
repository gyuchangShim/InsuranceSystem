package insurance;

import java.util.ArrayList;
import java.util.List;

public class InsuranceListImpl implements InsuranceList {

	private ArrayList<Insurance> insuranceList;

	public InsuranceListImpl(){
		insuranceList = new ArrayList<>();
	}

	@Override
	public void add(Insurance insurance){
		insuranceList.add(insurance);
	}

	@Override
	public void delete(int insuranceId){
		insuranceList.remove(insuranceId);
	}

	@Override
	public void retrieve(){

	}

	@Override
	public List<Insurance> getAllInsurance() {
		return insuranceList;
	}

	@Override
	public void updateReport(int insuranceId, String report) {
		insuranceList.get(insuranceId).setPlanReport(report);
	}

	@Override
	public void createDesign(Insurance insurance) {

	}

	@Override
	public void updateRate(float rate) {

	}

	@Override
	public void updateState(InsuranceState insuranceState) {

	}


}