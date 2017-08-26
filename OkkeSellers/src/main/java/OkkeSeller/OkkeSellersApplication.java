package OkkeSeller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import OkkeSeller.Config.SecurityUtility;
import OkkeSeller.Service.UserService;
import OkkeSeller.domain.User;
import OkkeSeller.domain.security.Role;
import OkkeSeller.domain.security.UserRole;

@SpringBootApplication
public class OkkeSellersApplication implements CommandLineRunner{
	
	@Autowired
	public UserService userService;
	
	

	public static void main(String[] args) {
		SpringApplication.run(OkkeSellersApplication.class, args);
	}	

	@Override 
	public void run(String... arg0) throws Exception {
		
		User user1=new User();
		user1.setId((long) 2);
		user1.setFirstName("Vikram");
		user1.setLastName("Pal");
		user1.setUsername("vikcykamboj");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("clandestine"));
		user1.setEmail("vikramvinayak@gmail.com");
		
		Set<UserRole> userRoles=new HashSet<>();
		
		Role role1=new Role();
		role1.setRoleID(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		userRoles.clear();
		
		User user2=new User();
		user2.setId((long) 3);
		user2.setFirstName("Rakesh");
		user2.setLastName("Kamboj");
		user2.setUsername("billions");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("clandestine98"));
		user2.setEmail("rakeshkamboj@gmail.com");
		
		Role role2=new Role();
		role2.setRoleID(2);
		role2.setName("ROLE_USER");
		userRoles.add(new UserRole(user2, role2));
		
		userService.createUser(user2, userRoles);
	}		
}