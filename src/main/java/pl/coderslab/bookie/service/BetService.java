package pl.coderslab.bookie.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.BetOption;
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
	List<Bet> createListOfBets(Game game);
	void saveBet (Bet bet);
	Bet homeBetByGame(Game game);
	Bet drawBetByGame(Game game);
	Bet awayBetByGame(Game game);
	Bet overBetByGame(Game game);
	Bet underBetByGame(Game game);
	void setInactive(Bet bet);
	List<Bet> easyBet();
	List<Bet> underdogBet();
	Bet underBetByGameId(long id);
	Bet overBetByGameId(long id);
}
