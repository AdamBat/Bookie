package pl.coderslab.bookie.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.BetOption;
import pl.coderslab.bookie.repositories.BetOptionRepository;
import pl.coderslab.bookie.service.BetOptionService;

@Service
public class BetOptionServiceImpl implements BetOptionService {
	@Autowired
	BetOptionRepository betOptionRepo;

	@Override
	public BetOption getById(long id) {
		return betOptionRepo.findOne(id);
	}

	@Override
	public List<BetOption> findAll() {
		return betOptionRepo.findAll();
	}

}
