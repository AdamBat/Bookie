package pl.coderslab.bookie.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table
@Entity
public class Game {
	@Id
	@GeneratedValue
	private long id;
	private String home;
	private String away;
	private LocalDateTime date;
	@ManyToOne
	private Event event;
	@OneToMany
	private List <BetOption> betOptions;
}
