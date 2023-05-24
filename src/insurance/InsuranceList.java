package insurance;

import java.util.List;

public interface InsuranceList {
    void add(Insurance insurance);
    void delete(int insuranceId);
    Insurance retrieve(int insuranceId);
    List<Insurance> retrieveAll();
    void update(Insurance insurance);
}