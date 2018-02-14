package pl.coderslab.bookie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.bookie.entities.Transaction;
import pl.coderslab.bookie.entities.User;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByUser(User user);
}
