package pl.coderslab.bookie.service;

import java.util.List;

import pl.coderslab.bookie.entities.Game;

public interface GameService {
	void addGame(Game game);
	List<Game> findAllActive();
	void checkAndUpdateIfActive();
}
