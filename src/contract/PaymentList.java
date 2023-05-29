package contract;

import java.util.List;
import java.util.Vector;

public interface PaymentList {
	
	void add(Payment payment);

    List<Payment> getAllregist();

    Vector<Payment> retrieveAll();
}
