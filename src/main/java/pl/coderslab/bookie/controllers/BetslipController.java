package pl.coderslab.bookie.controllers;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.exceptions.NotEnoughFundsException;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.BetSlipService;
import pl.coderslab.bookie.service.ConfirmedBetService;
import pl.coderslab.bookie.service.UserService;

@Controller
@RequestMapping("bets/betslip")
public class BetslipController {
	@Autowired
	BetService betService;
	@Autowired
	BetSlipService betslipService;
	@Autowired
	ConfirmedBetService confirmedBetService;
	@Autowired
	UserService userService;

	@ModelAttribute("betslip")
	public List<Bet> getBetslip() {
		return betslipService.getBetslip();
	}

	@RequestMapping("/add")
	public String addToBetslip(@RequestParam long id) {
		betslipService.addBet(betService.findOneById(id));
		return "redirect:/bets/betslip";
	}

	@RequestMapping("")
	public String showBetslip(Model model) {
		double odds = 1;
		for (Bet bet : betslipService.getBetslip()) {
			odds *= bet.getOdds();
		}
		DecimalFormat format = new DecimalFormat("#.##");
		odds = Double.valueOf(format.format(odds));
		ConfirmedBet confirmedBet = new ConfirmedBet();
		confirmedBet.setOdds(odds);
		confirmedBet.setBet(getBetslip());
		for (Bet b : getBetslip()) {
		}
		model.addAttribute("confirmedBet", confirmedBet);
		return "bets/betslip";
	}

	@ResponseBody
	@RequestMapping("/confirmBet")
	public String confirmBet(Authentication auth, @ModelAttribute ConfirmedBet confirmedBet) {
		confirmedBet.setBet(getBetslip());
		double odds = 1;
		for (Bet bet : betslipService.getBetslip()) {
			odds *= bet.getOdds();
		}
		confirmedBet.setOdds(odds);
		try {
			confirmedBetService.confirmBet(confirmedBet);
		} catch (NotEnoughFundsException e) {
			e.printStackTrace();
		}
		return "success";
	}

}
