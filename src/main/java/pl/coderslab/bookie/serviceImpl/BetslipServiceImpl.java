package pl.coderslab.bookie.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.BetSlipService;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BetslipServiceImpl implements BetSlipService {
	List<Bet> betslip = new ArrayList<>();

	@Override
	public void addBet(Bet bet) {
		if (betslip.isEmpty()) {
			betslip.add(bet);
		}
		boolean check = true;
		for (Bet b : betslip) {
			if (b.getGame().getAway().equals(bet.getGame().getAway())&& b.getGame().getHome().equals(bet.getGame().getHome())) {
				System.out.println("SIE POWTARZ");
				check = false;
			}

		}
		if (check == true) {
			System.out.println("dodajemy");
			betslip.add(bet);
		}

	}

	@Override
	public void removeBet(Bet bet) {
		for (Bet b : betslip) {
			if (b.getGame().getHome().equals(bet.getGame().getHome())
					&& b.getGame().getAway().equals(bet.getGame().getAway())) {
			} else {
				betslip.remove(bet);
			}
		}
	}

	@Override
	public List<Bet> getBetslip() {
		return betslip;
	}

}
