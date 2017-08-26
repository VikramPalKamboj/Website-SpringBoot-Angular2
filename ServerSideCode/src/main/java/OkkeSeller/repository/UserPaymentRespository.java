package OkkeSeller.repository;

import org.springframework.data.repository.CrudRepository;

import OkkeSeller.domain.security.UserPayment;

public interface UserPaymentRespository extends CrudRepository<UserPayment, Long>{


}
