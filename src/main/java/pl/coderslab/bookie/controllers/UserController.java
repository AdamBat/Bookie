package pl.coderslab.bookie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.Transaction;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/profile")
	public String account() {
		return "user/account";
	}
	@RequestMapping("/withdraw")
	public String withdraw(Model model) {
		model.addAttribute("transaction", new Transaction());
		return "user/withdraw";
	}
	@RequestMapping("/deposit")
	public String deposit(Model model) {
		model.addAttribute("transaction", new Transaction());
		return "user/deposit";
		
	}
}
