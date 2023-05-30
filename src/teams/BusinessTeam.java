package teams;
import business.*;
import util.Constants.Crud;
import util.Constants.Target;
import util.TuiReader;

import java.util.List;

public class BusinessTeam extends Team {

	private OperationPolicyList operationPolicyList;
	private SellGroupList sellGroupList;

	public BusinessTeam(OperationPolicyList operationPolicyList, SellGroupList sellGroupList){
		this.operationPolicyList = operationPolicyList;
		this.sellGroupList = sellGroupList;
	}

	@Override
	public void establishPolicy(Target target, Crud crud) {
		String policyInf = TuiReader.readInput("정확히 입력해주세요.");
		String[] policyInfList = policyInf.split("/");
		OperationPolicy operationPolicy = new OperationPolicy();
		operationPolicy.setName(policyInfList[0]);
		operationPolicy.setContent(policyInfList[1]);
		operationPolicyList.add(operationPolicy);
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

	public void evaluateResult(){
		String policyInf = TuiReader.readInput("정확히 입력해주세요.");
		String[] policyInfList = policyInf.split("/");
		SellGroup sellGroup = sellGroupList.retrieve(Integer.parseInt(policyInfList[0]));
		sellGroup.setExResult(policyInfList[1]);
		sellGroupList.update(sellGroup);
	}

	public List<OperationPolicy> getAllPolicy() {
		return operationPolicyList.retrieveAll();
	}
}
