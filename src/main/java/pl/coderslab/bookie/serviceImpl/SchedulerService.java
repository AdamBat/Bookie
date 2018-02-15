package pl.coderslab.bookie.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.GameService;

@Service
public class SchedulerService {

	@Autowired
	GameService gameService;
	@Autowired
	BetService betService;

	@Transactional
	public void updateGame() {
		List<Game> games = gameService.findAllActive();
		for (Game game : games) {
			if (game.getDateTime().isBefore(LocalDateTime.now())) {
				Game gameToUpdate = gameService.findOneById(game.getId());
				for (Bet bet : betService.findAllByGame(game)) {
					Bet betToUpdate = betService.findOneById(bet.getId());
					betToUpdate.setActive(false);

				}
				gameToUpdate.setActive(false);
				gameService.addGame(gameToUpdate);
				System.out.println(gameToUpdate.getAway() + " is inactive" + gameToUpdate.isActive());
			}
		}
		System.out.println(" is inactive");

	}

}
