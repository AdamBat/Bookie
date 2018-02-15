package pl.coderslab.bookie.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.repositories.BetRepository;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.CountryService;
import pl.coderslab.bookie.service.GameService;

@RestController
@RequestMapping("/rest")
public class Restcontroller {
	@Autowired
	CountryService countryService;
	@Autowired
	GameService gameService;
	@Autowired
	BetService betService;
	@Autowired
	BetRepository betRepo;

	@GetMapping("/odds")
	ResponseEntity getAllFixturesWIthOdds() {
		return ResponseEntity.ok(betRepo.findAll());
	}

	@GetMapping("/countries")
	ResponseEntity getAllCountries() {
		return ResponseEntity.ok(countryService.getAll());
	}
	@GetMapping
	ResponseEntity getAllFootballFixtures() {
		return ResponseEntity.ok(gameService.getAllFootball());
	}
}
