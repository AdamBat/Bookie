package pl.coderslab.bookie.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Country;
import pl.coderslab.bookie.repositories.CountryRepository;
import pl.coderslab.bookie.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	CountryRepository countryRepo;

	@Override
	public List<Country> getAll() {
		return countryRepo.findAll();
	}

}
