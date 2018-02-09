package pl.coderslab.bookie.service;

import java.util.List;

import pl.coderslab.bookie.entities.Sport;

public interface SportService {
	List<Sport> getAll();
	void addSport(Sport sport);
}
