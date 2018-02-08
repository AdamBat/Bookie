package pl.coderslab.bookie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.Match;
import pl.coderslab.bookie.service.LivescoreService;

@Controller
public class LivescoreController {
	@Autowired
	LivescoreService livescoreService;
	
	@ModelAttribute("games")
	public List<Match> getAllGames(){
		return livescoreService.getAllMatches();
	}
	@RequestMapping("/livescore")
	public String livescore() {
		return "livescore";
	}
	
	
}
