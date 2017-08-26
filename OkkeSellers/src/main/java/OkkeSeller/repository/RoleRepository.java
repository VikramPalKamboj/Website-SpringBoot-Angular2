package OkkeSeller.repository;

import org.springframework.data.repository.CrudRepository;

import OkkeSeller.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
