package OkkeSeller.repository.resources;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import OkkeSeller.Service.UserPaymentService;
import OkkeSeller.Service.UserService;
import OkkeSeller.domain.User;
import OkkeSeller.domain.security.UserBilling;
import OkkeSeller.domain.security.UserPayment;

@RestController
@RequestMapping("/payment")
public class PaymentResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private UserPaymentService userPaymentService;
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity addNewCreditCardPost(@RequestBody UserPayment userPayment, Principal principal){
		
		if (null!=principal) {
			User user=userService.findByUsername(principal.getName());
			UserBilling userBilling=userPayment.getUserBilling();
			
			userService.updateUserBilling(userBilling, userPayment, user);	
			
			
		}
		return new ResponseEntity("Payment added(update) successfully", HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseEntity removePaymentPost(@RequestBody String id, Principal principal){
		
		
		userPaymentService.removeById(Long.parseLong(id));
		return new ResponseEntity("Payment removed successfully", HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/setDefault", method=RequestMethod.POST)
	public ResponseEntity setDefaultPaymentPost(@RequestBody String id, Principal principal){
		
		User user=userService.findByUsername(principal.getName());

		userService.setDefaultPaymentPost(Long.parseLong(id), user);
		return new ResponseEntity("Set default payment method", HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/setDefault", method=RequestMethod.POST)
	public List<UserPayment> getUserPaymentList(Principal principal){
		
		User user=userService.findByUsername(principal.getName());

		List<UserPayment>  userPaymentList=user.getUserPayment();

		return userPaymentList;
		
	}
	
	
	

}
