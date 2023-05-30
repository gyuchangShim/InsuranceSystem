package teams;
import undewriting.AssumePolicy;
import undewriting.AssumePolicyList;
import util.Constants.Target;
import util.Constants.Crud;

import java.util.List;

public class UnderwritingTeam extends Team {

	public AssumePolicy assumePolicy;
	private AssumePolicyList assumePolicyList;

	public UnderwritingTeam(AssumePolicyList assumePolicyList){
		this.assumePolicyList = assumePolicyList;
	}

	public void finalize() throws Throwable {super.finalize();}

	public void examine(){

	}

	@Override
	public void establishPolicy(Target target, Crud crud) {
		if (target == Target.ASSUME_POLICY) {
			if (crud == Crud.CREATE) {
				this.assumePolicy = new AssumePolicy();
				String uwPolicy = util.TuiReader.readInput("인수 정책의 내용이 기입되지 않았습니다.");
				String[] policySplit = uwPolicy.split("/");
				assumePolicy.setName(policySplit[0]);
				assumePolicy.setContent(policySplit[1]);
				assumePolicy.setPolicyType(policySplit[2]);
				assumePolicyList.add(assumePolicy);
			}
		}
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

	// 인수 정책 제거 - 시나리오 X
	public AssumePolicy deletePolicy() {
		List<AssumePolicy> policyList = assumePolicyList.retreiveAll();
		for (int i = 0; i < policyList.size(); i++) {
			System.out.println(i + ". " + policyList.get(i).getPolicyID() + " " + policyList.get(i).getName());
		}
		int choice = util.TuiReader.choice(0, policyList.size() - 1);
		return policyList.get(choice);
	}
}