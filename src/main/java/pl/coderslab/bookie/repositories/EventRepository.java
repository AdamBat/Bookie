package pl.coderslab.bookie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findAllBySportId(long id);
}
