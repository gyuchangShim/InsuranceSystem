package undewriting;

import java.util.ArrayList;
import java.util.List;

public class AssumePolicyListImpl implements AssumePolicyList {

	public static ArrayList<AssumePolicy> assumePolicyList;
	public AssumePolicyListImpl(){
		this.assumePolicyList = new ArrayList<>();
	}

	public void finalize() throws Throwable {

	}

	public void add(AssumePolicy assumePolicy){
		assumePolicyList.add(assumePolicy);
	}
	public void delete(AssumePolicy assumePolicy){assumePolicyList.remove(assumePolicy);}

	public void retrieve(){

	}

	public void update(){

	}

	public List<AssumePolicy> getAllPolicy() {return assumePolicyList;}
}