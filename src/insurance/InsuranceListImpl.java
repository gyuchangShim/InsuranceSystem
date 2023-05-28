package insurance;

import java.util.ArrayList;
import java.util.List;

public class InsuranceListImpl implements InsuranceList {

	private ArrayList<Insurance> insuranceList;
	private int insuranceIdGenerator;

	public InsuranceListImpl(){
		insuranceList = new ArrayList<>();
		insuranceIdGenerator = 1;
	}

	@Override
	public void add(Insurance insurance){
		insurance.setInsuranceID(generateInsuranceId());
		insuranceList.add(insurance);
	}

	@Override
	public void delete(int insuranceId){
		insuranceList.remove(insuranceId);
	}

	@Override
	public Insurance retrieve(int insuranceId){
		return null;
	}

	@Override
	public List<Insurance> retrieveAll() {
		return insuranceList;
	}

	@Override
	public void update(Insurance insurance) {

	}

	private int generateInsuranceId() {
		return insuranceIdGenerator++;
	}
}