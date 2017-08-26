package OkkeSeller.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OkkeSeller.Service.UserPaymentService;
import OkkeSeller.domain.security.UserPayment;
import OkkeSeller.repository.UserPaymentRespository;


@Service
public class UserPaymentServiceImpl implements UserPaymentService {
	
	@Autowired
	private UserPaymentRespository userPaymentRespository;

	@Override
	public UserPayment findById(long id) {
		
		return userPaymentRespository.findOne(id);
		}

	@Override
	public void removeById(long id) {
		userPaymentRespository.delete(id);
	}

	
}
