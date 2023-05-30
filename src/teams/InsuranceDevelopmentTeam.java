package teams;

import insurance.Insurance;
import insurance.InsuranceList;
import exception.CSaveFailException;
import insurance.InsuranceState;
import util.Constants.Crud;
import util.Constants.Target;
import util.TuiReader;

public class InsuranceDevelopmentTeam extends Team {

	private InsuranceList insuranceList;

	public InsuranceDevelopmentTeam(InsuranceList insuranceList){
		this.insuranceList = insuranceList;
	}


	@Override
	public void establishPolicy(Target target, Crud crud) {

	}

	@Override
	public void manage(Target target, Crud crud) {

	}

	@Override
	public void plan(Target target, Crud crud) {
		if (target == Target.INSURANCE) {
			if (crud == Crud.CREATE) {
				String report = TuiReader.readInput("보고서가 올바른 형식이 아닙니다.");
				Insurance insurance = new Insurance();
				insurance.setPlanReport(report);
				try {
					insuranceList.add(insurance);
				} catch (Exception e) {
					e.printStackTrace();
					throw new CSaveFailException("보고서 저장에 실패했습니다.");
				}
			} else if (crud == Crud.UPDATE) {

			} else if (crud == Crud.DELETE) {

			}
		}
	}

	@Override
	public void process(Target target, Crud crud) {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void authorizeInsurance(){

	}

	public void uploadReport(){

	}

}