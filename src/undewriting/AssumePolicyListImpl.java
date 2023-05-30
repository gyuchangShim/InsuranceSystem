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

	@Override
	public void delete(int assumePolicyId) {

	}

	@Override
	public AssumePolicy retreive(int policyId) {
		return null;
	}

	@Override
	public List<AssumePolicy> retreiveAll() {
		return null;
	}

	@Override
	public void update(AssumePolicy assumePolicy) {

	}

	/*public void delete(AssumePolicy assumePolicy){assumePolicyList.remove(assumePolicy);}

	public void retrieve(){

	}

	public void update(){

	}

	public List<AssumePolicy> getAllPolicy() {return assumePolicyList;}*/
}