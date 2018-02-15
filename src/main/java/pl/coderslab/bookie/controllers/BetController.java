package pl.coderslab.bookie.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.Event;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.entities.Sport;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.ConfirmedBetService;
import pl.coderslab.bookie.service.EventService;
import pl.coderslab.bookie.service.GameService;
import pl.coderslab.bookie.service.SportService;
import pl.coderslab.bookie.service.UserService;

@Controller
@RequestMapping("/bets")
public class BetController {
	@Autowired
	SportService sportService;
	@Autowired
	EventService eventService;
	@Autowired
	GameService gameService;
	@Autowired
	BetService betService;
	@Autowired
	ConfirmedBetService confirmedBetService;
	@Autowired
	UserService userService;

	@ModelAttribute("sports")
	public List<Sport> sportsList() {
		return sportService.getAll();
	}

	@ModelAttribute("events")
	public List<Event> eventList() {
		return eventService.getAll();

	}
	@ModelAttribute("games")
	public List<Game> gamesList() {
		return gameService.findAllActive();
	}
		
	@RequestMapping("/all")
	public String allGames() {	
		return "bets/all";
	}
	@RequestMapping("/game")
	public String betPage(Model model,@RequestParam long id) {
		model.addAttribute("game",gameService.findOneById(id));
		model.addAttribute("home", betService.getHomeBetByGameId(id));
		model.addAttribute("draw", betService.getXBetByGameId(id));
		model.addAttribute("away", betService.getAwayBetByGameId(id));
		model.addAttribute("over", betService.overBetByGameId(id));
		model.addAttribute("under", betService.underBetByGameId(id));
		return "bets/game";
	}

	
	

	
}
