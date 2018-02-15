package pl.coderslab.bookie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.BetOption;
@Repository
public interface BetOptionRepository extends JpaRepository<BetOption, Long>{

}
