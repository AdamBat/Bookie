package pl.coderslab.bookie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table
@Entity
public class ConfirmedBet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Bet bet;
	private double stake;
	private boolean settled=false;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Bet getBet() {
		return bet;
	}
	public void setBet(Bet bet) {
		this.bet = bet;
	}
	public double getStake() {
		return stake;
	}
	public void setStake(double stake) {
		this.stake = stake;
	}
	public boolean isSettled() {
		return settled;
	}
	public void setSettled(boolean settled) {
		this.settled = settled;
	}
	
	
}

