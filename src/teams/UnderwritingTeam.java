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
		this.assumePolicy = new AssumePolicy();
		this.assumePolicyList = assumePolicyList;
	}

	public void finalize() throws Throwable {super.finalize();}

	public void examine(){

	}

	@Override
	public void establishPolicy(Target target, Crud crud) {
		if (target == Target.ASSUME_POLICY) {
			if (crud == Crud.CREATE) {
				String uwPolicy = util.TuiReader.readInput("인수 정책의 내용이 형식과 맞지 않습니다.");
				String[] policySplit = uwPolicy.split("/");
				assumePolicy.setName(policySplit[0]);
				assumePolicy.setContent(policySplit[1]);
				assumePolicy.setPolicyType(policySplit[2]);
				assumePolicyList.add(assumePolicy);
			} else if (crud == Crud.READ) {
				// 조회
				//List<AssumePolicy> policyList = AssumePolicyListImpl.getAllPolicy();
				//for (int i = 0; i < policyList.size(); i++) {
				//	System.out.println(i + ". " + policyList.get(i).getPolicy());
				//}
			} else if(crud == Crud.UPDATE) {
				// 수정 - 시나리오에서 존재 X
			} else if(crud == Crud.DELETE) {
				// 삭제 - 시나리오에서 존재 X
				AssumePolicy assumePolicy = deletePolicy();
				assumePolicyList.delete(assumePolicy);
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
		List<AssumePolicy> policyList = assumePolicyList.getAllPolicy();
		for (int i = 0; i < policyList.size(); i++) {
			System.out.println(i + ". " + policyList.get(i).getPolicyID() + " " + policyList.get(i).getName());
		}
		int choice = util.TuiReader.choice(0, policyList.size() - 1);
		return policyList.get(choice);
	}
}