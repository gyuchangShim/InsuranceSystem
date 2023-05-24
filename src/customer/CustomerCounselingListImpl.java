package customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerCounselingListImpl implements CustomerCounselingList{

    private List<CustomerCounseling> counselingList;

    public CustomerCounselingListImpl() {
        this.counselingList = new ArrayList<>();
    }

    @Override
    public void add(CustomerCounseling counseling) {

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
}
