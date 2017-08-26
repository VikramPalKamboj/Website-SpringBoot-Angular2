package OkkeSeller;

import org.springframework.data.repository.CrudRepository;

import OkkeSeller.domain.security.UserBilling;

public interface UserBillingRepository extends CrudRepository<UserBilling, Long> {

}
