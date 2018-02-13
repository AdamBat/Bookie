package pl.coderslab.bookie.controllers;

import java.security.Principal;

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
	public String loginConfirm(Principal principal) {
		System.out.println(principal.getName());
		return "livescore";
	}

}