package pl.coderslab.bookie.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Table
@Entity
public class Game {
	@Id
	@GeneratedValue
	private long id;
	private String home;
	private String away;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dateTime;
	@ManyToOne
	private Event event;
	@OneToMany
	private List<BetOption> betOptions;
	@Column(name = "active", nullable = false, columnDefinition = "boolean default false")
	private boolean active;
	@Column(name = "home_score", nullable = false, columnDefinition = "int default 0")
	private int homeScore;
	@Column(name = "away_score", nullable = false, columnDefinition = "int default 0")
	private int awayScore = 0;
	@Column(name = "settled", nullable = false, columnDefinition = "boolean default false")
	private boolean settled;

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	public LocalDateTime getDate() {
		return dateTime;
	}

	public void setDate(LocalDateTime date) {
		this.dateTime = date;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<BetOption> getBetOptions() {
		return betOptions;
	}

	public void setBetOptions(List<BetOption> betOptions) {
		this.betOptions = betOptions;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public boolean isSettled() {
		return settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
	}

}
