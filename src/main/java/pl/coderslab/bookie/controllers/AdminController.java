package pl.coderslab.bookie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.BetOption;
import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.Event;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.entities.Sport;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.repositories.SportRepository;
import pl.coderslab.bookie.repositories.UserRepository;
import pl.coderslab.bookie.service.BetOptionService;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.ConfirmedBetService;
import pl.coderslab.bookie.service.EventService;
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
	@Autowired
	EventService eventService;
	@Autowired
	BetOptionService betOptionService;
	@Autowired
	BetService betService;

	@ModelAttribute("users")
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	@ModelAttribute("bets")
	public List<ConfirmedBet> getAll() {
		return confirmedBetService.getAll();
	}

	@ModelAttribute("unsettledGames")
	public List<Game> getAllUnsettled() {
		return gameService.findAllInactiveandUnsettled();
	}
	@ModelAttribute("activeBets")
	public List<ConfirmedBet> activeBets(){
		return confirmedBetService.getAll();
	}

	@ModelAttribute("events")
	public List<Event> getEvents() {
		return eventService.getAll();
	}
	@ModelAttribute("gamesNoOdds")
	public List<Game> getAllGamesWithoutOdds(){
		return gameService.findAllWithoutOdds();
	}
	@ModelAttribute("games")
	public List<Game> getAllGames(){
		return gameService.findAllActive();
	}
	@ModelAttribute("betOptions")
	public List<BetOption> getAllBetOptions(){
		return betOptionService.findAll();
	}
	@RequestMapping("")
	public String adminHome() {
		return "admin/admin-home";
	}

	@RequestMapping("/users")
	public String users() {
		return "admin/users";
	}

	@RequestMapping("/bets")
	public String listAllBets() {
		return "admin/bets/all";
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

	@RequestMapping("/bets/addGame")
	public String addGame(Model model) {
		model.addAttribute("game", new Game());
		return "admin/bets/add";
	}

	@PostMapping("/bets/addGame")
	public String gameAdded(Game game) {
		game.setActive(true);
		gameService.addGame(game);
		return "redirect:/admin/";
	}
	@RequestMapping("/bets/games")
	public String allGames() {
		return "admin/bets/games";
	}
	@RequestMapping("/bets/setOdds")
	public String setOdds(Model model) {
		model.addAttribute("bet",new Bet());
		return "admin/bets/setOdds";
	}
	@PostMapping("/bets/setOdds")
	public String settleOdds(Bet bet) {
		bet.setActive(true);
		betService.saveBet(bet);
		return "redirect:/admin/bets/setOdds";
	}
}

/*
 * @RequestMapping("/bets/addGame/set1x2odds") public String
 * setOdds(@ModelAttribute Game game,Model model) { gameService.addGame(game);
 * List<Bet> bets = betService.createListOfBets(game); for(Bet b:bets) {
 * betService.saveBet(b); } model.addAttribute("bets", bets); return
 * "admin/bets/set1x2odds"; }
 * 
 * @PostMapping("/bets/addGame/set1x2odds") public String
 * confirmOdds(@ModelAttribute Bet bet) { betService.saveBet(bet);
 * System.out.println(bet.getGame().getAway()); return
 * "redirect:/bets/addGame/set1x2odds"; }
 * 
 * @RequestMapping("bets/addGame/acceptOdd") public String
 * accept(@ModelAttribute Bet bet) { betService.saveBet(bet); return
 * "redirect:/admin/bets/addGame/set1x2odds";
 */
