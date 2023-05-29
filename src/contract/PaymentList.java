package contract;

import java.util.List;

public interface PaymentList {
	
	void add(Payment payment);

    List<Payment> getAllregist();

}
