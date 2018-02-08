package pl.coderslab.bookie.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.repositories.GameRepository;
import pl.coderslab.bookie.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	GameRepository gameRepo;

	@Override
	public void addGame(Game game) {
		gameRepo.save(game);
	}
//iterating over all active Games every minute, if they already started we're setting as inactive (impossible to bet on)
	@Override
	@Scheduled(fixedRate=60000)
	public void checkAndUpdateIfActive() {
	List<Game>games = findAllActive();
		for(Game game:games) {
			if(game.getDateTime().isBefore(LocalDateTime.now())) {
				System.out.println(game.getHome());
				Game toUpdate = gameRepo.findOne(game.getId());
				toUpdate.setActive(false);
				gameRepo.save(toUpdate);			
			}
		}
		
	}

	@Override
	public List<Game> findAllActive() {
		return gameRepo.findAllActive();
	}

}
