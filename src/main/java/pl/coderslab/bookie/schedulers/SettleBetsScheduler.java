package pl.coderslab.bookie.schedulers;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.service.ConfirmedBetService;
import pl.coderslab.bookie.service.UserService;

@Component
public class SettleBetsScheduler {
	@Autowired
	ConfirmedBetService confirmedBetService;
	@Autowired
	UserService userService;

	/*
	 * iterating over all unsettled coupons,then checking all bets involved in it,if
	 * all settled as won we settle coupon as a winner,if any bet of it is lost
	 * coupon is lost,if any is unsettled we leave coupon it as it is
	 */	@Scheduled(fixedRate = 60000)
	@Transactional
	public void checkAndSettleBets() {
		for (ConfirmedBet coupon : confirmedBetService.getAllUnsettled()) {
			List<Bet> bets = coupon.getBet();
			if (bets.stream().allMatch(bet -> bet.isSettled())) {
				if (bets.stream().allMatch(bet -> bet.isWon())) {
					coupon.setWon(true);
					User user = coupon.getUser();
					BigDecimal winAmount = new BigDecimal(coupon.getStake() * coupon.getOdds());
					coupon.setWinAmount(winAmount.doubleValue());
					user.setBalance(user.getBalance().add(winAmount));
					userService.updateUser(user);
					confirmedBetService.setConfirmedBetAsSettled(coupon);

				} else {
					coupon.setWon(false);
					confirmedBetService.setConfirmedBetAsSettled(coupon);

				}
			}

		}
	}

}
