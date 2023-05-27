package customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerCounselingListImpl implements CustomerCounselingList{

    private List<CustomerCounseling> counselingList;
    private int customerCounselingIdGenerator;

    public CustomerCounselingListImpl() {
        this.counselingList = new ArrayList<>();
        this.customerCounselingIdGenerator = 1;
    }

    @Override
    public void add(CustomerCounseling counseling) {
        int counselingId = generateCustomerCounselingId();
        counseling.setCounselingId(counselingId);
        counselingList.add(counseling);
    }

    @Override
    public void delete(int counselingId) {

    }

    @Override
    public void retrieve() {

    }

    @Override
    public List<CustomerCounseling> retrieveAll() {
        return null;
    }

    @Override
    public void update(CustomerCounseling counseling) {

    }

    private int generateCustomerCounselingId() {
        return customerCounselingIdGenerator++;
    }
}
