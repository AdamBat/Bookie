package pl.coderslab.bookie.service;

import java.util.List;

import pl.coderslab.bookie.entities.BetOption;

public interface BetOptionService {
	BetOption getById(long id);
	List<BetOption> findAll();
}
