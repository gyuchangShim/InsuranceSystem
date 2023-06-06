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
//            throw new RuntimeException("SMS 전송에 실패했습니다. 개발부서에 문의해주세요.");
//            Thread.sleep(2days);
            return 0.6f;
        };
    }

    public static Callable<LocalDateTime> authorizedInsurance(Insurance insurance) {
        return () -> {
//        throw new RuntimeException("SMS 전송에 실패했습니다. 개발부서에 문의해주세요.");
            insurance.setInsuranceState(InsuranceState.AUTHORIZED);
            return LocalDateTime.now();
        };
    }
    public static void runProgram(CampaignProgram campaignProgram) {
        campaignProgram.setProgramState(CampaignState.RUN);
    }

    public static boolean collaborateUW(Contract contract, int incomeLevel) {
        if(incomeLevel == 1) {
            contract.setContractRunState(ContractRunState.DENY);
            return false;
        } else {
            contract.setContractRunState(ContractRunState.FINISH);
            return true;
        }
    }

    public static void sendSMStoCustomer(String s) {
//        throw new Exception("메시지 전송에 실패했습니다.");
    }
}
