package teams;
import undewriting.AssumePolicy;
import undewriting.AssumePolicyList;
import undewriting.PolicyType;
import util.Constants.Target;
import util.Constants.Crud;
import util.TuiReader;

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
		boolean correctPolicy = false;
		while(!correctPolicy) {
			if (target == Target.ASSUME_POLICY) {
				if (crud == Crud.CREATE) {
					this.assumePolicy = new AssumePolicy();
					String uwPolicy = util.TuiReader.readInput("인수 정책의 내용이 형식과 맞지 않습니다.");
					String[] policySplit = uwPolicy.split("/");
					if (policySplit.length != 3) {
						TuiReader.readInput("인수 정책의 내용이 기입되지 않았습니다.");
						continue;
					}
					assumePolicy.setName(policySplit[0]);
					assumePolicy.setContent(policySplit[1]);
					assumePolicy.setPolicyType(PolicyType.valueOf(policySplit[2]));
					assumePolicyList.add(assumePolicy);
					correctPolicy = true;
				}
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

}