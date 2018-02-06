package pl.coderslab.bookie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity
public class Bet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Game game;
	@ManyToOne
	private BetOption betOption;
	private double oddsHome;
	private double oddsX;
	private double oddsAway;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public BetOption getBetOption() {
		return betOption;
	}
	public void setBetOption(BetOption betOption) {
		this.betOption = betOption;
	}
	public double getOddsHome() {
		return oddsHome;
	}
	public void setOddsHome(double oddsHome) {
		this.oddsHome = oddsHome;
	}
	public double getOddsX() {
		return oddsX;
	}
	public void setOddsX(double oddsX) {
		this.oddsX = oddsX;
	}
	public double getOddsAway() {
		return oddsAway;
	}
	public void setOddsAway(double oddsAway) {
		this.oddsAway = oddsAway;
	}
	
	
	
	
	
	
}
