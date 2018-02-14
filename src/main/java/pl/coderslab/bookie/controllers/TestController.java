package pl.coderslab.bookie.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.entities.Role;
import pl.coderslab.bookie.entities.Transaction;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.repositories.RoleRepository;
import pl.coderslab.bookie.service.GameService;
import pl.coderslab.bookie.service.TransactionService;
import pl.coderslab.bookie.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	UserService userService;
	@Autowired
	GameService gameService;
	@Autowired
	TransactionService transactionService;
	@Autowired
	RoleRepository repo;
	
	
	@ResponseBody
	@RequestMapping("/addAdmin")
	public String addAdmin() {
		Role role1 = new Role();
		Role role2 = new Role();
		role1.setName("ROLE_ADMIN");
		role2.setName("ROLE_USER");
		repo.save(role1);
		repo.save(role2);
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");;
		user.setEmail("ds@op.pl");
		user.setPostcode("434");
		user.setHouseNr(230);
		user.setStreet("sasasa");
		user.setDateOfBirth(LocalDate.now());
		System.out.println(user.getId());
		userService.saveAdmin(user);
		System.out.println(user.getId());
		User user2 = new User();
		user2.setUsername("user");
		user2.setPassword("user");;
		user2.setEmail("ds@ofp.pl");
		user2.setPostcode("43fd4");
		user2.setHouseNr(230);
		user2.setStreet("sassaasa");
		user2.setDateOfBirth(LocalDate.now());
		userService.saveUser(user2);
		return"admin added";
	}
	@ResponseBody
	@RequestMapping("/checkTrans")
	public String checkTrans () {
		User user = userService.getCurrentUser();
		System.out.println(user.getEmail());
		for(Transaction t:transactionService.findByUser(user) ){
			System.out.println(t.getName());
		}
		return "check";
	}
	
	
}
