package pl.coderslab.bookie.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.repositories.BetRepository;
import pl.coderslab.bookie.service.BetService;

@Service
public class BetServiceImpl implements BetService {
	@Autowired
	BetRepository betRepo;
	private static final int MIN_MARGIN_PERCENT = 3;
	private static final int MAX_MARGIN_PERCENT = 10;

	// we're checking if our proposed odds gives us enough house edge return false
	// if odds are to big or to small
	@Override
	public boolean checkIfMarginIsOkTwoWay(double homeOdds, double awayOdds) {
		double margin = ((1 / homeOdds) * 100 + (1 / awayOdds) * 100) - 100;
		return margin >= MIN_MARGIN_PERCENT && margin <= MAX_MARGIN_PERCENT;
	}

	@Override
	public boolean checkIfMarginIsOkThreeWay(double homeOdds, double drawOdds, double awayOdds) {
		double margin = ((1 / homeOdds) * 100 + (1 / drawOdds) * 100 + (1 / awayOdds) * 100) - 100;
		return margin >= MIN_MARGIN_PERCENT && margin <= MAX_MARGIN_PERCENT;
	}

	@Override
	public Bet getHomeBetByGameId(long id) {
		return betRepo.getHomeBetByGameId(id);
	}

	@Override
	public Bet getXBetByGameId(long id) {
		return betRepo.getXBetByGameId(id);
	}

	@Override
	public Bet getAwayBetByGameId(long id) {
		return betRepo.getAwayBetByGameId(id);
	}

	@Override
	public Bet findOneById(long id) {
		return betRepo.findOne(id);
	}

	@Override
	public void settlleBet(Bet bet) {
		long gameId = bet.getGame().getId();
		long betOpt = bet.getBetOption().getId();
		int scoreHome = bet.getGame().getHomeScore();
		int scoreAway = bet.getGame().getAwayScore();
		int total = scoreHome + scoreAway;
		// if home win we set all 1,1X and 12 bets as won
		if (scoreHome > scoreAway && (betOpt == 1 || betOpt == 4 || betOpt == 6) || betOpt == 9) {
			bet.setActive(false);
			bet.setWon(true);
		} // if draw we set all X,1X,X2 bets as won
		if (scoreHome == scoreAway && (betOpt == 2 || betOpt == 4 || betOpt == 5) || betOpt == 9 || betOpt == 8) {
			bet.setActive(false);
			bet.setWon(true);
		} // if away win all 2,X2,12 bets as won
		if (scoreAway > scoreHome && (betOpt == 3 || betOpt == 5 || betOpt == 6)) {
			bet.setActive(false);
			bet.setWon(true);
		}
		if (scoreHome - 1 > scoreAway && betOpt == 7) {
			bet.setActive(false);
			bet.setWon(true);
			if (scoreAway - 1 > scoreHome && betOpt == 10) {
				bet.setActive(false);
				bet.setWon(true);
			}
			if(total>2&& betOpt==11) {
				bet.setActive(false);
				bet.setWon(true);
			}
			if(total<3 && betOpt==12) {
				bet.setActive(false);
				bet.setWon(true);
			}
			
		} else {
			bet.setActive(false);
			bet.setWon(false);
		}

	}

}
