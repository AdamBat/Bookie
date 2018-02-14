package pl.coderslab.bookie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.exceptions.NotEnoughFundsException;
import pl.coderslab.bookie.repositories.ConfirmedBetRepository;

public interface ConfirmedBetService {
	void confirmBet(ConfirmedBet confirmedBet) throws NotEnoughFundsException;
	List<ConfirmedBet> getAll();
	
}
