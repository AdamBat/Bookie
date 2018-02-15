package pl.coderslab.bookie.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.repositories.GameRepository;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	GameRepository gameRepo;
	@Autowired
	BetService betService;

	@Override
	public void addGame(Game game) {
		gameRepo.save(game);
		System.out.println("dodajemy" + game.isActive());
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
		game.setActive(false);
		game.setSettled(true);
		
		for(Bet b:betService.findAllByGame(game)) {
			betService.settleBet(b);
		}
		
		gameRepo.save(game);
	}


	@Override
	public List<Game> findAllInactiveandUnsettled() {
		return gameRepo.findAllByActiveAndSettled(false, false);
	}


	@Override
	public List<Game> findAllWithoutOdds() {
		List<Game> gamesNoOdds = new ArrayList<>();
		for(Game game:findAllActive()) {
			if(betService.findAllByGame(game).isEmpty()) {
				gamesNoOdds.add(game);
			}
		}
		return gamesNoOdds;
	}


	@Override
	public List<Game> findAllWithOdds() {
		List<Game> gamesWithOdds = new ArrayList<>();
		for(Game game:findAllActive()) {
			if(!betService.findAllByGame(game).isEmpty()) {
				gamesWithOdds.add(game);
			}
		}
		return gamesWithOdds;
	}


	@Override
	public void addActiveGame(Game game) {
		game.setActive(true);
		gameRepo.save(game);
	}
	
	
	
}
