package outerActor;

import contract.Contract;
import contract.ContractRunState;
import insurance.Insurance;
import insurance.InsuranceState;
import java.util.concurrent.Callable;
import marketingPlanning.CampaignProgram;
import marketingPlanning.CampaignState;
import java.time.LocalDateTime;

public class OuterActor {

    public static Callable<Float> calcInsuranceRate(int payment, int riskDegree) {
        return () -> {
//            Thread.sleep(2days);
            return 0.6f;
        };
    }

    public static Callable<LocalDateTime> authorizedInsurance(Insurance insurance) throws Exception {
        return () -> {
//        throw new Exception("보험 인가 실패");
            insurance.setInsuranceState(InsuranceState.AUTHORIZED);
            return LocalDateTime.now();
        };
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

    public static void sendSMStoCustomer(String s) throws Exception {
//        throw new Exception("메시지 전송에 실패했습니다.");
        System.out.println("고객에게 SMS 전송: " + s);
    }
}
