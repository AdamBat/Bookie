package pl.coderslab.bookie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.entities.Sport;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.repositories.SportRepository;
import pl.coderslab.bookie.repositories.UserRepository;
import pl.coderslab.bookie.service.ConfirmedBetService;
import pl.coderslab.bookie.service.GameService;
import pl.coderslab.bookie.service.SportService;
import pl.coderslab.bookie.service.UserService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	ConfirmedBetService confirmedBetService;
	@Autowired
	GameService gameService;
		
	@ModelAttribute("users")
	public List<User> getAllUsers() {
		return userService.getAll();
	}
	@ModelAttribute("bets")
	public List<ConfirmedBet> getAll(){
		return confirmedBetService.getAll();
	}
	
	@ModelAttribute("unsettledGames")
	public List<Game> getAllUnsettled(){
		return gameService.findAllInactiveandUnsettled();
	}
	
	@RequestMapping("/users")
	public String users() {
		return "admin/users";		
	}
	
	@RequestMapping("/bets")
	public String listAllBets() {
		return "admin/bets";
	}
	@RequestMapping("/settle")
	public String settleGames() {
		return "admin/bets/settle-games";
	}
	@PostMapping("/settle")
	public String settleGame(@ModelAttribute Game game) {
		gameService.settleGame(game.getId(), game.getHomeScore(), game.getAwayScore());
		return "redirect:/admin/settle";
	}
	
}
