package pl.coderslab.bookie.service;

import java.util.List;

import pl.coderslab.bookie.entities.Bet;

public interface BetSlipService {
	void addBet(Bet bet);
	void removeBet (Bet bet);
	List<Bet> getBetslip();
	
}
