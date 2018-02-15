package pl.coderslab.bookie.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.GameService;

@Component
public class MarginCheckScheduler {
	@Autowired
	GameService gameService;
	@Autowired
	BetService betService;
	
	/*iterating over every game and then we check if margin for 1X2 and over/under bets is between 5 and 10 %
	if it is not we set all involved bets as inactive*/
	@Scheduled(fixedRate=600000000)
	public void marginCheck1X2() {
		try {
		for(Game game:gameService.findAllWithOdds()) {
				double homeOdds = betService.homeBetByGame(game).getOdds();
				double awayOdds = betService.awayBetByGame(game).getOdds();
				double drawOdds = betService.drawBetByGame(game).getOdds();
				if(!betService.checkIfMarginIsOkThreeWay(homeOdds, drawOdds, awayOdds)) {
					Bet betH = betService.homeBetByGame(game);
					
					Bet betD = betService.drawBetByGame(game);
					betD.setActive(false);		
					betService.setInactive(betD);
					Bet betA = betService.awayBetByGame(game);
					betA.setActive(false);
					betService.setInactive(betA);
					betService.setInactive(betD);
					betService.setInactive(betH);

				}
				double overOdds = betService.overBetByGame(game).getOdds();
				double underOdds = betService.underBetByGame(game).getOdds();
				if(!betService.checkIfMarginIsOkTwoWay(underOdds, overOdds)) {
					betService.overBetByGame(game).setActive(false);
					betService.underBetByGame(game).setActive(false);
				}
			
		}
	}
		catch(NullPointerException e ) {
		}
	}
	
}
