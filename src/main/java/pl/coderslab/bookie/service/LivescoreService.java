package pl.coderslab.bookie.service;

import java.util.List;

import pl.coderslab.bookie.entities.Match;

public interface LivescoreService {
	List<Match> getAllMatches();
}
