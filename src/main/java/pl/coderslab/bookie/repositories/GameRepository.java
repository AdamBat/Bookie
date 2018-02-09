package pl.coderslab.bookie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.entities.Sport;
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
	@Query("select g from Game g where active=true")
	List<Game> findAllActive();
	List<Game> findAllByEventId(long id);
}
