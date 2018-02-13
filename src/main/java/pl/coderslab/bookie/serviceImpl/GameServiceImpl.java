package pl.coderslab.bookie.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

	// iterating over all active Games every minute, if they already started we're
	// setting as inactive (impossible to bet on)
	@Override
	@Scheduled(fixedRate = 60000)
	public void checkifActiveAndUpdate() {
		List<Game> games = findAllActive();
		for (Game game : games) {
			if (game.getDateTime().isBefore(LocalDateTime.now())) {
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

	@Override
	public List<Game> getAllFootball() {
		List<Game> football = new ArrayList<>();
		football.addAll(getAllENG());
		football.addAll(getAllGER());
		football.addAll(getAllFRA());
		football.addAll(getAllITA());
		football.addAll(getAllSPA());

		return football;
	}

	@Override
	public List<Game> getAllENG() {
		return gameRepo.findAllByEventIdAndActive(1, true);
	}

	@Override
	public List<Game> getAllGER() {
		return gameRepo.findAllByEventIdAndActive(2, true);
	}

	@Override
	public List<Game> getAllSPA() {
		return gameRepo.findAllByEventIdAndActive(3, true);
	}
	@Override
	public List<Game> getAllITA() {
		return gameRepo.findAllByEventIdAndActive(4, true);
	}

	@Override
	public List<Game> getAllFRA() {
		return gameRepo.findAllByEventIdAndActive(5, true);
	}

	@Override
	public List<Game> getAllBasketball() {
		List<Game> basket = new ArrayList<>();
		basket.addAll(getAllNBA());
		basket.addAll(getAllEuro());
		return basket;
	}

	@Override
	public List<Game> getAllTennis() {
		return gameRepo.findAllByEventIdAndActive(8, true);
	}

	@Override
	public List<Game> getAllBoxing() {
		return gameRepo.findAllByEventIdAndActive(10, true);
	}

	@Override
	public List<Game> getAllNBA() {
		return gameRepo.findAllByEventIdAndActive(6, true);
	}

	@Override
	public List<Game> getAllEuro() {
		return gameRepo.findAllByEventIdAndActive(7, true);
	}
	

	@Override
	public Game findOneById(long id) {
		return gameRepo.findOne(id);
	}

	@Override
	public void settleGame(long gameId, int home, int away) {
		Game game = gameRepo.findOne(gameId);
		game.setHomeScore(home);
		game.setAwayScore(away);
	}


}
