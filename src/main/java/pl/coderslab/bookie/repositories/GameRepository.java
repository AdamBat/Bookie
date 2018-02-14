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
//	@Query("SELECT * FROM ")
	List<Game> findAllByEventIdAndActive(long id,boolean active);
	List<Game> findAllByActiveAndSettled(boolean active,boolean settled);
}
/*select b.fname, b.lname from Users b JOIN b.groups c where c.groupName = :groupName 
SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
FROM Orders
INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;*/