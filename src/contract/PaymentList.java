package contract;

import java.util.List;
import java.util.Vector;

public interface PaymentList {
	
	void add(Payment payment);
	void delete( int paymentID );
	Payment retrieve( int paymentID );
    List<Payment> retrieveAll();
    void update( Payment payment );
}
