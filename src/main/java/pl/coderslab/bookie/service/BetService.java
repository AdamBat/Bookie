package pl.coderslab.bookie.service;

import org.springframework.data.jpa.repository.Query;

import pl.coderslab.bookie.entities.Bet;

public interface BetService {
	boolean checkIfMarginIsOkTwoWay(double homeOdds,double awayOdds);
	boolean checkIfMarginIsOkThreeWay(double homeOdds,double drawOdds,double awayOdds);
	Bet getHomeBetByGameId(long id);
	Bet getXBetByGameId (long id);
	Bet getAwayBetByGameId(long id);
	Bet findOneById(long id);
	void settlleBet(Bet bet);
}
