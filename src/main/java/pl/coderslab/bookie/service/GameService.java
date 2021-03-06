package pl.coderslab.bookie.service;

import java.util.List;

import pl.coderslab.bookie.entities.Game;

public interface GameService {
	void addGame(Game game);
	void addActiveGame(Game game);
	Game findOneById(long id);
	List<Game> findAllActive();
	List<Game> getAllFootball();
	List<Game> getAllENG();
	List<Game> getAllGER();
	List<Game> getAllSPA();
	List<Game> getAllITA();
	List<Game> getAllFRA();
	List<Game> getAllBasketball();
	List<Game> getAllNBA();
	List<Game> getAllEuro();
	List<Game> getAllTennis();
	List<Game> getAllBoxing();
	void settleGame(long gameId, int home, int away);
	List<Game> findAllInactiveandUnsettled();
	List<Game> findAllWithoutOdds();
	List<Game> findAllWithOdds();
}
