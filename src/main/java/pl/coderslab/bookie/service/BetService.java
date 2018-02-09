package pl.coderslab.bookie.service;


public interface BetService {
	boolean checkIfMarginIsOkTwoWay(double homeOdds,double awayOdds);
	boolean checkIfMarginIsOkThreeWay(double homeOdds,double drawOdds,double awayOdds);
}
