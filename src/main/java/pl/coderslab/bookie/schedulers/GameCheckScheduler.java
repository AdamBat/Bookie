package pl.coderslab.bookie.schedulers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.GameService;

public class GameCheckScheduler {
	@Autowired
	GameService gameService;
	@Autowired
	BetService betService;
	
	// iterating over all active Games every minute, if they already started we're
	// setting as inactive,as well as all bets regarding this game (impossible to bet on)

	@Scheduled(fixedRate = 60000)
	public void checkifActiveAndUpdate() {
		List<Game> games = gameService.findAllActive();
		for (Game game : games) {
			if (game.getDateTime().isBefore(LocalDateTime.now())) {
				Game gameToUpdate = gameService.findOneById(game.getId());
				for(Bet bet:betService.findAllByGame(game)) {
					Bet betToUpdate = betService.findOneById(bet.getId());
					betToUpdate.setActive(false);
					
				}
				gameToUpdate.setActive(false);
				gameService.addGame(gameToUpdate);
			}
		}

	}
}
