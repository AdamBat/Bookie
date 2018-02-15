package pl.coderslab.bookie.serviceImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.exceptions.NotEnoughFundsException;
import pl.coderslab.bookie.repositories.BetRepository;
import pl.coderslab.bookie.repositories.ConfirmedBetRepository;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.BetSlipService;
import pl.coderslab.bookie.service.ConfirmedBetService;
import pl.coderslab.bookie.service.UserService;

@Service
public class ConfirmedBetServiceImpl implements ConfirmedBetService {
	@Autowired
	ConfirmedBetRepository confirmedBetRepo;
	@Autowired
	UserService userService;
	@Autowired
	BetSlipService betSlipService;
	@Autowired
	BetService betService;

	@Override
	@Transactional
	public void confirmBet(ConfirmedBet confirmedBet) throws NotEnoughFundsException {
		User user = userService.findByUsername(userService.getCurrentUser().getUsername());
		confirmedBet.setUser(user);
		if (user.getBalance().doubleValue() < confirmedBet.getStake()) {
			if (user.getBalance().doubleValue() + user.getBonusBalance().doubleValue() < confirmedBet.getStake()) {
				throw new NotEnoughFundsException();
			}
			else {
				double bonusStake = confirmedBet.getStake()-user.getBalance().doubleValue();
				user.addBet(confirmedBet);
				user.setBalance(new BigDecimal(0.0));
				user.setBonusBalance(user.getBonusBalance().subtract(new BigDecimal(bonusStake)));
				userService.updateUser(user);
				confirmedBetRepo.save(confirmedBet);
				betSlipService.clearBetslip();
				
			}
		} else {
			user.setBalance(user.getBalance().subtract(new BigDecimal(confirmedBet.getStake())));
			confirmedBetRepo.save(confirmedBet);
			user.addBet(confirmedBet);
			userService.updateUser(user);
			betSlipService.clearBetslip();
		}
		
	}

	@Override
	public List<ConfirmedBet> getAll() {
		return confirmedBetRepo.findAll();
	}

	@Override
	public List<ConfirmedBet> getAllByUser(User user) {
		return confirmedBetRepo.findAllByUser(user);
	}

	@Override
	public List<ConfirmedBet> getAllSettledByUser(User user) {
		return confirmedBetRepo.findAllByUserAndSettled(user, true);
	}

	@Override
	public List<ConfirmedBet> getAllUnsettledByUser(User user) {
		return confirmedBetRepo.findAllByUserAndSettled(user, false);

	}

	@Override
	public List<ConfirmedBet> getAllWonBetByUser(User user) {
		return confirmedBetRepo.findAllByUserAndIsWon(user, true);
	}

	@Override
	public List<ConfirmedBet> getAllUnsettled() {
		return confirmedBetRepo.findAllBySettled(false);
	}


	@Override
	public void setConfirmedBetAsSettled(ConfirmedBet bet) {
		ConfirmedBet toUpdate = confirmedBetRepo.findOne(bet.getId());
		toUpdate.setSettled(true);
		toUpdate.setWinAmount(bet.getWinAmount());
		toUpdate.setWon(bet.isWon());
		confirmedBetRepo.save(toUpdate);
	}

	@Override
	public ConfirmedBet setEasyCoupon() {
		return null;
	}

	@Override
	public ConfirmedBet setUnderdogCoupon() {
		return null;
	}

	@Override
	public double getTotalOdds(ConfirmedBet bet) {
		double odds =1;
		for(Bet b:bet.getBet()) {
			odds*=b.getOdds();
		}
		DecimalFormat format = new DecimalFormat("#.##");
		odds = Double.valueOf(format.format(odds));
		
		return odds;
	}

}
