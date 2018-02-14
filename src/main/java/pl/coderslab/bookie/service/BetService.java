package pl.coderslab.bookie.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.Game;

public interface BetService {
	boolean checkIfMarginIsOkTwoWay(double homeOdds,double awayOdds);
	boolean checkIfMarginIsOkThreeWay(double homeOdds,double drawOdds,double awayOdds);
	Bet getHomeBetByGameId(long id);
	Bet getXBetByGameId (long id);
	Bet getAwayBetByGameId(long id);
	Bet findOneById(long id);
	List<Bet> findAllByGame(Game game);
	void settleBet(Bet bet);
}
