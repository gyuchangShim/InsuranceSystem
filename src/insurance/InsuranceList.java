package insurance;

import java.util.List;

public interface InsuranceList {

    void add(Insurance insurance);

    void delete(Insurance insurance);

    void retrieve();

    List<Insurance> getAllInsurance();

    void update();
}