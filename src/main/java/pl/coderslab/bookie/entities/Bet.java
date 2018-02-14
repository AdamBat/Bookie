package pl.coderslab.bookie.entities;

import javax.persistence.Column;
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
	private double odds;
	private boolean active=true;
	@Column(name = "won", nullable = false, columnDefinition = "boolean default false")
	private boolean won;
	@Column(name = "settled", nullable = false, columnDefinition = "boolean default false")
	private boolean settled;
	
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
	public double getOdds() {
		return odds;
	}
	public void setOdds(double odds) {
		this.odds = odds;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isWon() {
		return won;
	}
	public void setWon(boolean won) {
		this.won = won;
	}
	
	public boolean isSettled() {
		return settled;
	}
	public void setSettled(boolean settled) {
		this.settled = settled;
	}
	@Override
	public String toString() {
		return "Bet [id=" + id + ", game=" + game + ", betOption=" + betOption + ", odds=" + odds + ", active=" + active
				+ ", won=" + won + "]";
	}
	
	
	
	
	
	
	
	
}
