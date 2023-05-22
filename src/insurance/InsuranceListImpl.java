package insurance;

import java.util.ArrayList;
import java.util.List;

public class InsuranceListImpl implements InsuranceList {

	private static ArrayList<Insurance> insuranceList;

	public InsuranceListImpl(){
		insuranceList = new ArrayList<>();
	}

	public void add(Insurance insurance){
		insuranceList.add(insurance);
	}

	public void delete(Insurance insurance){
		insuranceList.remove(insurance);
	}

	public void retrieve(){

	}

	@Override
	public List<Insurance> getAllInsurance() {
		return insuranceList;
	}

	public void update(){

	}

}