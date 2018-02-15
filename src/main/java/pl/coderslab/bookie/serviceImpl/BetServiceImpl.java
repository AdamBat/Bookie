package pl.coderslab.bookie.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.BetOption;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.repositories.BetRepository;
import pl.coderslab.bookie.service.BetOptionService;
import pl.coderslab.bookie.service.BetService;

@Service
public class BetServiceImpl implements BetService {
	@Autowired
	BetRepository betRepo;
	private static final int MIN_MARGIN_PERCENT = 3;
	private static final int MAX_MARGIN_PERCENT = 10;
	@Autowired
	BetOptionService betOptionService;
	/* we're checking if our proposed odds gives us enough house edge, return false
	if odds are to big or to small*/
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
	public void settleBet(Bet bet) {
		boolean keepChecking =true;
		long betOpt = bet.getBetOption().getId();
		Bet toUpdate = betRepo.findOne(bet.getId());
		toUpdate.setSettled(true);
		toUpdate.setActive(false);
		int scoreHome = bet.getGame().getHomeScore();
		int scoreAway = bet.getGame().getAwayScore();


		int total = scoreHome + scoreAway;
		/*if home win with more than 1 goal we set 1,1X,12, home-1,5,and home+1,5 as won*/
		if (scoreHome - 1 > scoreAway && (betOpt == 7 || betOpt == 1 || betOpt==4 ||betOpt==6 ||betOpt==9)) {		
			toUpdate.setWon(true);
		}	/*if away win with more than 1 goal we set 1,1X,12, away-1,5,and away+1,5 as won*/
		if (scoreAway - 1 > scoreHome && (betOpt == 10 || betOpt == 8 || betOpt==3 || betOpt==5 || betOpt==6)) {
			toUpdate.setWon(true);
		}
		/* if home win we set all 1,1X,12,and +1,5 bets as won*/
		if (scoreHome > scoreAway && (betOpt == 1 || betOpt==4 ||betOpt==6 ||betOpt==9)) {
			toUpdate.setWon(true);
			
		} /* if draw we set all X,1X,X2,home+1,5 and away +1,5 bets as won*/
		if (scoreHome == scoreAway && (betOpt == 2 || betOpt == 4 || betOpt == 5 || betOpt == 9 || betOpt == 8)) {			
			toUpdate.setWon(true);	
		}  /*if away win all 2,X2,12,away+1,5 bets as won*/
		if (scoreAway > scoreHome && (betOpt == 3 || betOpt == 5 || betOpt == 6 || betOpt==8)) {
			toUpdate.setWon(true);	
		}
		//if total goals over 2 all over 2,5  goals won
		if (total > 2 && betOpt == 11) {
			toUpdate.setWon(true);
			
		}/* if total under 3 goals all under 2,5 goals set as won*/
		if (total < 3 && betOpt == 12) {
			toUpdate.setWon(true);
			}
		else {			
		}
		betRepo.save(toUpdate);
	}

	@Override
	public List<Bet> findAllByGame(Game game) {
		return betRepo.findAllByGame(game);
	}

	@Override
	public List<Bet> createListOfBets(Game game) {
		List<Bet> bets = new ArrayList<>();
		for(int i=1;i<13;i++) {
			Bet bet = new Bet();
			bet.setBetOption(betOptionService.getById(i));
			bet.setGame(game);
			bets.add(bet);
		}
		return bets;
	}

	@Override
	public void saveBet(Bet bet) {
		betRepo.save(bet);
	}

	@Override
	public Bet homeBetByGame(Game game) {
		return betRepo.findByGameAndBetOption(game, betOptionService.getById(1));
	}

	@Override
	public Bet drawBetByGame(Game game) {
		return betRepo.findByGameAndBetOption(game, betOptionService.getById(2));
	}

	@Override
	public Bet awayBetByGame(Game game) {
		return betRepo.findByGameAndBetOption(game, betOptionService.getById(3));
	}

	@Override
	public Bet overBetByGame(Game game) {
		return betRepo.findByGameAndBetOption(game, betOptionService.getById(11));
	}

	@Override
	public Bet underBetByGame(Game game) {
		return betRepo.findByGameAndBetOption(game, betOptionService.getById(12));
	}

	@Override
	public void setInactive(Bet bet) {
		Bet toUpdate = betRepo.findOne(bet.getId());
		toUpdate.setActive(false);

		betRepo.save(toUpdate);
	}

	@Override
	public List<Bet> easyBet() {
		return betRepo.findFirst5ByOddsAndActive();
	}

	@Override
	public List<Bet> underdogBet() {
		return betRepo.findFirst5ByOddsAndActiveDesc();
	}

	public BetServiceImpl(BetRepository betRepo) {
		this.betRepo = betRepo;
	}
	@Override
	public Bet overBetByGameId(long id) {
		return betRepo.getOverBetByGameId(id);
	}

	@Override
	public Bet underBetByGameId(long id) {
		return betRepo.getUnderBetByGameId(id);
	}

}
