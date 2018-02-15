package pl.coderslab.bookie.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.BetOption;
import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.service.BetOptionService;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.BetSlipService;
import pl.coderslab.bookie.service.ConfirmedBetService;
import pl.coderslab.bookie.service.GameService;
import pl.coderslab.bookie.service.UserService;

@Controller
@RequestMapping("/picks")
public class BossPicksController {
	@Autowired
	BetService betService;
	@Autowired
	GameService gameService;
	@Autowired
	BetOptionService betOptionService;
	@Autowired
	BetSlipService betSlipService;
	@Autowired
	UserService userService;
	@Autowired
	ConfirmedBetService confirmedBetService;

	@ModelAttribute("easy")
	public List<Bet> lowestOdds() {
		return betService.easyBet();
	}

	@ModelAttribute("hard")
	public List<Bet> highestOdds() {
		return betService.underdogBet();
	}

	@RequestMapping("")
	public String showBossPage(Model model) {
		User user = userService.getCurrentUser();
		ConfirmedBet easy = new ConfirmedBet();
		easy.setBet(lowestOdds());
		easy.setUser(user);
		easy.setOdds(confirmedBetService.getTotalOdds(easy));
		ConfirmedBet hard =  new ConfirmedBet();
		hard.setBet(highestOdds());
		hard.setUser(user);
		hard.setOdds(confirmedBetService.getTotalOdds(hard));
		model.addAttribute("hardBet", hard);
		model.addAttribute("easyBet", easy);
		
		
		return "admin/picks";
	}

}