package Customer;

import java.util.List;

public interface CustomerCounselingList {
    void add(CustomerCounseling counseling);
    void delete(int counselingId);
    void retrieve();
    List<CustomerCounseling> retrieveAll();
    void update(CustomerCounseling counseling);

}
