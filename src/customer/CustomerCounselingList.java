package customer;

import java.util.List;

public interface CustomerCounselingList {
    void add(CustomerCounseling counseling);
    void delete(int counselingId);
    CustomerCounseling retrieve(int counselingId);
    List<CustomerCounseling> retrieveAll();
    void update(CustomerCounseling counseling);

}
