package pl.coderslab.bookie.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.service.UserService;

@Controller
public class TestController {
	@Autowired
	UserService userService;
	@RequestMapping("/addAdmin")
	public String addAdmin() {
		User user = new User();
		user.setUsername("adam");
		user.setUsername("adam");
		user.setEmail("ds@op.pl");
		user.setPostcode("434");
		user.setHouseNr(230);
		user.setStreet("sasasa");
		user.setDateOfBirth(LocalDate.now());
		userService.saveAdmin(user);
		return"user/register-confirmation";
	}
}
