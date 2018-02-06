package pl.coderslab.bookie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.Country;
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
