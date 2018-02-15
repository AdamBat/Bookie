package pl.coderslab.bookie.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Table
@Entity
public class ConfirmedBet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Bet> bet;
	private LocalDateTime created;	
	private double stake;
	private double winAmount;
	@Column(name = "settled", nullable = false, columnDefinition = "boolean default false")
	private boolean settled=false;
	@Column(name = "won", nullable = false, columnDefinition = "boolean default false")
	private boolean isWon =false;
	private double odds;
	
	
	public double getOdds() {
		return odds;
	}
	public void setOdds(double odds) {
		this.odds = odds;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public double getWinAmount() {
		return winAmount;
	}
	public void setWinAmount(double winAmount) {
		this.winAmount = winAmount;
	}
	public boolean isWon() {
		return isWon;
	}
	public void setWon(boolean isWon) {
		this.isWon = isWon;
	}
	
	
}

