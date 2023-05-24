package outerActor;

import insurance.Insurance;
import insurance.InsuranceState;
import java.time.LocalDateTime;

public class OuterActor {

    public static float calcInsuranceRate(int payment, int riskDegree) {
        return 0.6f;
    }

    public static LocalDateTime authorizedInsurance(Insurance insurance) {
        insurance.setInsuranceState(InsuranceState.AUTHORIZED);
        return LocalDateTime.now();
    }
}
