package pl.coderslab.bookie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.ConfirmedBet;
import pl.coderslab.bookie.entities.User;
@Repository 
public interface ConfirmedBetRepository extends JpaRepository<ConfirmedBet, Long> {
	List<ConfirmedBet> findAllByUser(User user);
	List<ConfirmedBet> findAllByUserAndSettled(User user,boolean settled);
	List<ConfirmedBet> findAllByUserAndIsWon (User user,boolean isWon);
	List<ConfirmedBet> findAllBySettled(boolean settled);
}
