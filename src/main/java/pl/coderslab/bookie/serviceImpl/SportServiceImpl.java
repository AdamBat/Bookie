package pl.coderslab.bookie.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Sport;
import pl.coderslab.bookie.repositories.SportRepository;
import pl.coderslab.bookie.service.SportService;

@Service
public class SportServiceImpl implements SportService {
	@Autowired
	SportRepository sportRepo;

	@Override
	public List<Sport> getAll() {
		return sportRepo.findAll();
	}

	@Override
	public void addSport(Sport sport) {
		sportRepo.save(sport);
	}

}
