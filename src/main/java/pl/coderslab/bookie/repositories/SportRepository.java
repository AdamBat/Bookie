package pl.coderslab.bookie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.Sport;
@Repository
public interface SportRepository  extends JpaRepository<Sport, Long>{

}
