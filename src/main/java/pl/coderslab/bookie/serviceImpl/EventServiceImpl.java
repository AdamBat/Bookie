package pl.coderslab.bookie.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Event;
import pl.coderslab.bookie.repositories.EventRepository;
import pl.coderslab.bookie.service.EventService;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	EventRepository eventRepo;

	@Override
	public List<Event> getAll() {
		return eventRepo.findAll();
	}

}
