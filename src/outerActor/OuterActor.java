package outerActor;

import contract.Contract;
import contract.ContractRunState;
import insurance.Insurance;
import insurance.InsuranceState;
import marketingPlanning.CampaignProgram;
import marketingPlanning.CampaignState;
import java.time.LocalDateTime;

public class OuterActor {

    public static float calcInsuranceRate(int payment, int riskDegree) {
        return 0.6f;
    }

    public static LocalDateTime authorizedInsurance(Insurance insurance) {
        insurance.setInsuranceState(InsuranceState.AUTHORIZED);
        return LocalDateTime.now();
    }
    public static void runProgram(CampaignProgram campaignProgram) {
        // 외부 캠페인 팀 캠페인 프로그램 실행
        campaignProgram.setProgramState(CampaignState.RUN);
    }

    public static boolean collaborateUW(Contract contract, int incomeLevel) {
        // 외부 Actor는 전달받은 계약에 대해 상태 변경 : Ready -> Finish
        if(incomeLevel == 1) {
            contract.setContractRunState(ContractRunState.DENY);
            return false;
        } else {
            contract.setContractRunState(ContractRunState.FINISH);
            return true;
        }
    }

    public static void sendSMS(String s) {
        System.out.println("SMS 전송: " + s);
    }
}
