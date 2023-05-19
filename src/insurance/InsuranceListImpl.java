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
	public void delete(Insurance insurance){
		insuranceList.remove(insurance);
	}

	@Override
	public void retrieve(){

	}

	@Override
	public List<Insurance> getAllInsurance() {
		return insuranceList;
	}

	@Override
	public void update(){

	}

}