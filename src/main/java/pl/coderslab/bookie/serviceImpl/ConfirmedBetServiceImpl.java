package pl.coderslab.bookie.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.repositories.ConfirmedBetRepository;
import pl.coderslab.bookie.service.ConfirmedBetService;

@Service
public class ConfirmedBetServiceImpl implements ConfirmedBetService {
	@Autowired
	ConfirmedBetRepository confirmedBetRepo;

	@Override
	public void confirmBet(ConfirmedBet confirmedBet) {
		confirmedBetRepo.save(confirmedBet);
	}

	@Override
	public List<ConfirmedBet> getAll() {
		return confirmedBetRepo.findAll();
	}
	
	
}
