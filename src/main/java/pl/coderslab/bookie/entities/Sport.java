package pl.coderslab.bookie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity
public class Sport {
	@Id
	@GeneratedValue
	private long id;
	private String name;
}
