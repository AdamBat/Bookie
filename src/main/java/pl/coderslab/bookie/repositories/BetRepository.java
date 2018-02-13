package pl.coderslab.bookie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.Bet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
	@Query(value = "Select * from bet where bet_option_id=1 AND game_id=?1 ",nativeQuery=true)
	Bet getHomeBetByGameId(long id);
	@Query(value = "Select * from bet where bet_option_id=2 AND game_id=?1 ",nativeQuery=true)
	Bet getXBetByGameId (long id);
	@Query(value = "Select *  from bet where bet_option_id=3 AND game_id=?1 ",nativeQuery=true)
	Bet getAwayBetByGameId(long id);
	
	
}
