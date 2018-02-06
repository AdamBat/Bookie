package pl.coderslab.bookie.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login() {
		return "user/login-form";
	}
	@PostMapping("/login")
	public String loginConfirm(Authentication auth) {
		if (auth.getAuthorities().contains("ADMIN")) {
			return "/admin/admin-home";
		} else if (auth.getAuthorities().contains("USER")) {
			return "/user/home";
		} else {
			return "/start/home";
		}
	}
}
