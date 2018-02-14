package pl.coderslab.bookie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Transaction;
import pl.coderslab.bookie.entities.User;


public interface TransactionService {
	void addTransaction(Transaction transaction) throws Exception;
	List<Transaction> findByUser(User user);
}
