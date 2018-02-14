package pl.coderslab.bookie.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.bookie.entities.Transaction;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.service.TransactionService;
import pl.coderslab.bookie.service.UserService;

@Controller
@RequestMapping("/account")
public class BankingController {
	@Autowired
	UserService userService;
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping("/banking")
	public String banking(Model model,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("transaction", new Transaction());
		model.addAttribute("user", user);
		return "user/banking";
	}
	@PostMapping("/deposit")
	public String deposit(Transaction transaction,Model model) {
		User user = userService.getCurrentUser();
		transaction.setName("deposit");
		try {
			transactionService.addTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("user", user);
		model.addAttribute("transaction", transaction);
		return "user/transaction-confirmation";
		
	}
	@PostMapping("/withdraw")
	public String withdraw(Transaction transaction,Model model) {
		User user = userService.getCurrentUser();
		transaction.setName("withdraw");
		try {
			transactionService.addTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("user", user);
		model.addAttribute("transaction", transaction);
		return "user/transaction-confirmation";
	}
}
