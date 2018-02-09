package pl.coderslab.bookie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.ConfirmedBet;
@Repository 
public interface ConfirmedBetRepository extends JpaRepository<ConfirmedBet, Long> {

}
