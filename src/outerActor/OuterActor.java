package outerActor;

import contract.Contract;
import contract.ContractRunState;
import insurance.Insurance;
import insurance.InsuranceState;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
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
        campaignProgram.setProgramState(CampaignState.RUN);
    }

    public static void collaborateUW(Contract contract) {
        // 외부 Actor는 전달받은 계약에 대해 상태 변경 : Ready -> Finish
        contract.setContractRunState(ContractRunState.FINISH);
    }

    public static void sendSMS(String s) {
        System.out.println("SMS 전송: " + s);
    }
}
