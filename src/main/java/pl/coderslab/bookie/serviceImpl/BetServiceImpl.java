package pl.coderslab.bookie.serviceImpl;

import org.springframework.stereotype.Service;

import pl.coderslab.bookie.service.BetService;

@Service
public class BetServiceImpl implements BetService {
	private static final int MIN_MARGIN_PERCENT = 3;
	private static final int MAX_MARGIN_PERCENT = 10;
//we're checking if our proposed odds gives us enough house edge return false if odds are to big or to small
	@Override
	public boolean checkIfMarginIsOkTwoWay(double homeOdds, double awayOdds) {
		double margin = ((1 / homeOdds) * 100 + (1 / awayOdds) * 100)-100;
		return margin>=MIN_MARGIN_PERCENT&& margin<=MAX_MARGIN_PERCENT;
	}

	@Override
	public boolean checkIfMarginIsOkThreeWay(double homeOdds, double drawOdds, double awayOdds) {
		double margin = ((1 / homeOdds) * 100 + (1 / drawOdds) * 100 + (1 / awayOdds) * 100)-100;
		return margin>=MIN_MARGIN_PERCENT&& margin<=MAX_MARGIN_PERCENT;
	}

}
