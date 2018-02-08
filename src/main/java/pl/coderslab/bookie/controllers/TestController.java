package pl.coderslab.bookie.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.service.GameService;
import pl.coderslab.bookie.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	UserService userService;
	@Autowired
	GameService gameService;
	@ResponseBody
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
		System.out.println(user.getId());
		userService.saveAdmin(user);
		System.out.println(user.getId());
		return"admin added";
	}
	@RequestMapping("/check")
	@ResponseBody
	public String check() {
		List<Game> games = gameService.findAllActive();
		gameService.checkAndUpdateIfActive();
		return "zmieniono";
	}
	
	
}
