package teams;
import business.*;
import exception.SOPPolicyNotFoundException;
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
		if(crud.equals(Crud.CREATE)){
			String policyInf = TuiReader.readInput("정확히 입력해주세요.");
			String[] policyInfList = policyInf.split("/");
			OperationPolicy operationPolicy = new OperationPolicy();
			operationPolicy.setName(policyInfList[0]);
			operationPolicy.setContent(policyInfList[1]);
			operationPolicy.setRating(0);
			operationPolicy.setPass(0);
			operationPolicyList.add(operationPolicy);
		}else{
			OperationPolicy operationPolicy = operationPolicyList.retrieve(Integer.parseInt(TuiReader.readInput("정확히 입력해주세요.")));
			if(operationPolicy.getPass()==0){
				operationPolicy.setPass(1);
				operationPolicyList.update(operationPolicy);
			}else {
				throw new SOPPolicyNotFoundException("해당 번호의 건의된 운영 방침이 없습니다.");
			}
		}
	}

	@Override
	public void manage(Target target, Crud crud) {
		OperationPolicy operationPolicy = operationPolicyList.retrieve(Integer.parseInt(TuiReader.readInput("정확히 입력해주세요.")));
		if(operationPolicy.getPass()==0){
			operationPolicy.setRating(operationPolicy.getRating()+1);
			operationPolicyList.update(operationPolicy);
		}else {
			throw new SOPPolicyNotFoundException("건의된 운영 방침이 없습니다.");
		}
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
