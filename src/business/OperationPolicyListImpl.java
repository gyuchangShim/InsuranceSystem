package business;

import java.util.ArrayList;
import java.util.List;

public class OperationPolicyListImpl implements OperationPolicyList {

	private List<OperationPolicy> operationPolicyList;
	private int operationPolicyId;

	public OperationPolicyListImpl(){
		operationPolicyList = new ArrayList<>();
		operationPolicyId = 1;
	}
	@Override
	public void add(OperationPolicy operationPolicy) {
		operationPolicy.setPolicyID(generateOperationPolicyId());
		operationPolicyList.add(operationPolicy);
	}
	@Override
	public void delete(int operationPolicyId) {

	}
	@Override
	public OperationPolicy retrieve(int operationPolicyId) {
		return operationPolicyList.stream()
			.filter(operationPolicy -> operationPolicy.getPolicyID() == operationPolicyId)
			.findFirst()
			.get();
	}
	@Override
	public void update(OperationPolicy operationPolicy) {

	}
	@Override
	public List<OperationPolicy> retrieveAll() {
		return operationPolicyList;
	}
	private int generateOperationPolicyId() {
		return operationPolicyId++;
	}
}