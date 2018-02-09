package pl.coderslab.bookie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.Event;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.entities.Sport;
import pl.coderslab.bookie.service.EventService;
import pl.coderslab.bookie.service.GameService;
import pl.coderslab.bookie.service.SportService;

@Controller
@RequestMapping("/bets")
public class BetController {
	@Autowired
	SportService sportService;
	@Autowired
	EventService eventService;
	@Autowired
	GameService gameService;

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
		return "user/bets/all";
	}

	
}
