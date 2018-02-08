package pl.coderslab.bookie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.bookie.entities.Bet;

public interface BetRepository extends JpaRepository<Bet, Long> {

}
