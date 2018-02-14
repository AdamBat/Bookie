package pl.coderslab.bookie.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
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
}