package Teams;

import insurance.Insurance;
import insurance.InsuranceList;
import exception.CSaveFailException;
import java.util.List;
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
					throw new CSaveFailException("보고서 저장에 실패했습니다.");
				}
				System.out.println("새 상품 기획안을 저장하였습니다.");
			} else if (crud == Crud.UPDATE) {
				Insurance insurance = choicePlan();
				System.out.println("기존 기획안: " + insurance.getPlanReport());
				System.out.println("수정할 기획안을 입력해주세요.");
				String report = TuiReader.readInput("보고서가 올바른 형식이 아닙니다.");
				insurance.setPlanReport(report);
				System.out.println("상품 기획안을 수정하였습니다.");
			} else if (crud == Crud.DELETE) {
				Insurance insurance = choicePlan();
				insuranceList.delete(insurance);
				System.out.println("해당 상품 기획안을 삭제하였습니다.");
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

	private Insurance choicePlan(){
		System.out.println("********************* 보험 기획안 *********************");
		System.out.println("수정하거나 삭제할 보험 기획안의 번호를 입력해주세요.");
		List<Insurance> insuranceList = this.insuranceList.getAllInsurance();
		for (int i = 0; i < insuranceList.size(); i++) {
			System.out.println(i + ". " + insuranceList.get(i).getPlanReport());
		}
		int choice = TuiReader.choice();
		while (choice < 0 || choice >= insuranceList.size()) {
			System.out.println("정확히 선택해주세요.");
			choice = TuiReader.choice();
		}
		return insuranceList.get(choice);
	}
}