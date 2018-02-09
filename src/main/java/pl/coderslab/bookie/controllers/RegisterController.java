package pl.coderslab.bookie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.Country;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.repositories.CountryRepository;
import pl.coderslab.bookie.service.CountryService;
import pl.coderslab.bookie.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	CountryService countryService;
	@Autowired
	UserService userService;

	@ModelAttribute("countries")
	public List<Country> getAll() {
		return countryService.getAll();
	}

	@RequestMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}

	@PostMapping("/register")
	public String register(User user) {
		if(user.getPassword().equals(user.getConfirmPassword())) {
		userService.saveUser(user);
		return "user/register-confirmation";
		}
		else {
			return "index";
		}
	}
}
