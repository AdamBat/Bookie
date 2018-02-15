package pl.coderslab.bookie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.Transaction;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.service.ConfirmedBetService;
import pl.coderslab.bookie.service.TransactionService;
import pl.coderslab.bookie.service.UserService;

@RequestMapping("/account/history")
@Controller
public class UserHistoryController {
	@Autowired
	ConfirmedBetService confirmedBetService;
	@Autowired
	UserService userService;
	@Autowired
	TransactionService transactionService;
	
	@ModelAttribute("settled")
	public List<ConfirmedBet> settled(){
		User user = userService.getCurrentUser();
		return confirmedBetService.getAllSettledByUser(user);
	}
	@ModelAttribute("unsettled")
	public List<ConfirmedBet> unsettled(){
		User user = userService.getCurrentUser();
		return confirmedBetService.getAllUnsettledByUser(user);
	}
	@ModelAttribute("all")
	public List<ConfirmedBet> all(){
		User user = userService.getCurrentUser();
		return confirmedBetService.getAllByUser(user);
	}
	@ModelAttribute("transactions")
	public List<Transaction> transactions(){
		User user = userService.getCurrentUser();
		return transactionService.findByUser(user);
	}
	@RequestMapping("")
	public String historyPage() {
		return "user/history/main";
	}
	@RequestMapping("/bets/all")
	public String betsView() {
		return "user/history/all";
	}
	@RequestMapping("/bets/settled")
	public String settledView() {
		return "user/history/settled";
	}
	@RequestMapping("/bets/unsettled")
	public String unsettledView() {
		return "user/history/unsettled";
	}
	@RequestMapping("/transactions")
	public String getAllTransactions() {
		return "user/history/transactions";
	}
	
}
