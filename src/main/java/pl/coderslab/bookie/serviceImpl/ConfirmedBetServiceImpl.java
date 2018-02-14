package pl.coderslab.bookie.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.exceptions.NotEnoughFundsException;
import pl.coderslab.bookie.exceptions.notEnoughFundsException;
import pl.coderslab.bookie.repositories.ConfirmedBetRepository;
import pl.coderslab.bookie.service.ConfirmedBetService;
import pl.coderslab.bookie.service.UserService;

@Service
public class ConfirmedBetServiceImpl implements ConfirmedBetService {
	@Autowired
	ConfirmedBetRepository confirmedBetRepo;
	@Autowired
	UserService userService;
	
	@Override
	public void confirmBet(ConfirmedBet confirmedBet) throws NotEnoughFundsException {
		User user = userService.getCurrentUser();
		confirmedBet.setUser(user);
		if(user.getBalance().doubleValue()<confirmedBet.getStake()) {
			throw new NotEnoughFundsException();
		}
		else {
			user.setBalance(user.getBalance().subtract(new BigDecimal(confirmedBet.getStake())));
			
		}
		confirmedBetRepo.save(confirmedBet);
	}

	@Override
	public List<ConfirmedBet> getAll() {
		return confirmedBetRepo.findAll();
	}
	
	
}
