package OkkeSeller.Service;

import java.util.Set;

import org.springframework.stereotype.Service;

import OkkeSeller.domain.User;
import OkkeSeller.domain.security.UserBilling;
import OkkeSeller.domain.security.UserPayment;
import OkkeSeller.domain.security.UserRole;

@Service
public interface UserService  {

	 User createUser(User user, Set<UserRole> userRoles);

	User save(User user);

	User findById(Long id);

	User findByUsername(String userName);

	User findByEmail(String userEmail);

	void updateUserPaymentInfo(UserBilling userBilling,
			UserPayment userPayment, User user);

	void updateUserBilling(UserBilling userBilling, UserPayment userPayment,
			User user);
	void setDefaultPaymentPost(Long userPaymentId, User user);

	
}
