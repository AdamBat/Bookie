package pl.coderslab.bookie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	User findByUsername(String username);

}
