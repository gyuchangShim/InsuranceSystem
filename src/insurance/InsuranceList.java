package insurance;

import java.util.List;

public interface InsuranceList {
    void add(Insurance insurance);
    void delete(int insuranceId);
    void retrieve();
    List<Insurance> getAllInsurance();
    void update(Insurance insurance);
}