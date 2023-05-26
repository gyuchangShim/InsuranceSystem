package insurance;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class InsuranceListImpl implements InsuranceList {

	private ArrayList<Insurance> insuranceList;

	public InsuranceListImpl(){
		insuranceList = new ArrayList<Insurance>();
	}
	
	public Vector<Insurance> getInsuranceByCustomerID( int customerID ){
		// 해당 고객이 가입한 모든 보험 정보를 가져온다.
		return null;
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


}