package Teams;

import insurance.Insurance;
import insurance.InsuranceListImpl;
import Teams.Team;
import exception.CSaveFailException;
import java.util.List;
import util.TuiReader;

public class InsuranceDevelopmentTeam extends Team {

	private InsuranceListImpl insuranceListImpl;

	public InsuranceDevelopmentTeam(){
		this.insuranceListImpl = new InsuranceListImpl();
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void authorizeInsurance(){

	}

	public void uploadReport(){

	}

	@Override
	public void establishPolicy(int diff1, int diff2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manage(int diff1, int diff2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void plan(int diff1, int crud) {
		if (diff1 == 1) {
			if (crud == 1) {
				String report = TuiReader.readInput("보고서가 올바른 형식이 아닙니다.");
				Insurance insurance = new Insurance();
				insurance.setPlanReport(report);
				try {
					insuranceListImpl.add(insurance);
				} catch (Exception e) {
					throw new CSaveFailException("보고서 저장에 실패했습니다.");
				}
				System.out.println("새 상품 기획안을 저장하였습니다.");
			} else if (crud == 3) {
				Insurance insurance = choicePlan();
				System.out.println("기존 기획안: " + insurance.getPlanReport());
				System.out.println("수정할 기획안을 입력해주세요.");
				String report = TuiReader.readInput("보고서가 올바른 형식이 아닙니다.");
				insurance.setPlanReport(report);
				System.out.println("상품 기획안을 수정하였습니다.");
			} else if (crud == 4) {
				Insurance insurance = choicePlan();
				insuranceListImpl.delete(insurance);
				System.out.println("해당 상품 기획안을 삭제하였습니다.");
			}
		}
	}

	@Override
	public void process(int diff1, int diff2) {
		// TODO Auto-generated method stub
		
	}

	private Insurance choicePlan(){
		System.out.println("********************* 보험 기획안 *********************");
		System.out.println("수정하거나 삭제할 보험 기획안의 번호를 입력해주세요.");
		List<Insurance> insuranceList = insuranceListImpl.getAllInsurance();
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