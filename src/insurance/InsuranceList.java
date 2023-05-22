package insurance;

import java.util.List;

public interface InsuranceList {

    void add(Insurance insurance);

    void delete(int insuranceId);

    void retrieve();

    List<Insurance> getAllInsurance();

    void updateReport(int insuranceId, String report);

    void createDesign(Insurance insurance);

    void updateRate(float rate);

    void updateState(int insuranceID, InsuranceState insuranceState);
}