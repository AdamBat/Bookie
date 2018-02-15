package pl.coderslab.bookie.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.bookie.entities.Country;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.service.CountryService;
import pl.coderslab.bookie.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;
	@Autowired
	CountryService countryService;

	@ModelAttribute("countries")
	public List<Country> getAll() {
		return countryService.getAll();
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@PostMapping("/home")
	public String loginConfirm(Authentication auth) {
		boolean isUser = auth.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equalsIgnoreCase("ROLE_USER"));
		boolean isAdmin = auth.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));
		if (isAdmin) {
			return "admin/admin-home";
		} else if (isUser) {
			return "user/user-home";
		}
		return "login-form";

	}

	@RequestMapping("/account/profile")
	public String userProfile(Model model) {
		User user = userService.getCurrentUser();
		model.addAttribute("user", user);
		return "user/profile";
	}
}