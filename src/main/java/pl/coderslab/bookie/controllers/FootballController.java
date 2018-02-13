package pl.coderslab.bookie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.service.GameService;

@RequestMapping("/bets/football")
@Controller
public class FootballController {
	@Autowired
	GameService gameService;

	@ModelAttribute("all")
	public List<Game> all(Model model) {
		return gameService.getAllFootball();
	}
	@ModelAttribute("eng")
	public List<Game> getAllBPL(){
		return gameService.getAllENG();
	}
	@ModelAttribute("ger")
	public List<Game> getAllBundes(){
		return gameService.getAllGER();
	}
	@ModelAttribute("spa")
	public List<Game> getAllPD(){
		return gameService.getAllSPA();
	}
	@ModelAttribute("fra")
	public List<Game> getAllL1(){
		return gameService.getAllFRA();
	}
	
	@RequestMapping("")
	public String all() {
		return "bets/football";
	}
	@RequestMapping("/laliga")
	public String primeraDivision() {
		return "bets/laliga";
				
	}
	@RequestMapping("/premier")
	public String premierLeague() {
		return "bets/bpl";
	}
	@RequestMapping("/bundes")
	public String bundesliga() {
		return "bets/bundesliga";
	}
	@RequestMapping("/seriea")
	public String serieA() {
		return "bets/serieA";
	}
	@RequestMapping("/ligue1")
	public String ligue1() {
		return "bets/ligue1";
	}
}

