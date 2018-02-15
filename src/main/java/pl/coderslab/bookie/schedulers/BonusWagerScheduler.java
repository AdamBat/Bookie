package pl.coderslab.bookie.schedulers;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.service.UserService;

@Component
public class BonusWagerScheduler {
	@Autowired
	UserService userService;

	/*
	 * Iterating over all users,checking if they have any bonus balance,and if they
	 * have rollover over 1000,we transfer their bonus balance to withdrawable
	 * balance
	 */
	@Scheduled(fixedRate = 6000)
	@Transactional
	public void checkIfBonusWagered() {
		for (User user : userService.getAll()) {
			if (user.getBonusBalance().doubleValue() > 0) {
				BigDecimal wagered = new BigDecimal(0);
				for (ConfirmedBet bet : user.getActiveBets()) {

					wagered = wagered.add(new BigDecimal(bet.getStake()));
				}
				if (wagered.compareTo(new BigDecimal(1000.0)) > 0) {
					user.setBalance(user.getBalance().add(user.getBonusBalance()));
					user.setBonusBalance(new BigDecimal(0.0));
					userService.saveUser(user);

				}

			}
		}
	}
}