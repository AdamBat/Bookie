package pl.coderslab.bookie.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Table
@Entity
public class ConfirmedBet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private User user;
	@OneToMany
	private List<Bet> bet;
	private LocalDateTime created;
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	private double stake;
	private boolean settled=false;
	private boolean won =false;
	private double odds;
	
	
	public double getOdds() {
		return odds;
	}
	public void setOdds(double odds) {
		this.odds = odds;
	}
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
	
	public List<Bet> getBet() {
		return bet;
	}
	public void setBet(List<Bet> bet) {
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
	@PrePersist
	protected void onCreate() {
		created = LocalDateTime.now();

	}
	
}

