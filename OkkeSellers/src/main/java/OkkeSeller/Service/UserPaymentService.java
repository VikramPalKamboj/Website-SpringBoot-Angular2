package OkkeSeller.Service;

import OkkeSeller.domain.security.UserPayment;

public interface UserPaymentService {

	UserPayment findById(long id);
	
	void removeById(long id);
	
	
	
	
}
