package pl.coderslab.bookie.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.coderslab.bookie.entities.Transaction;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.repositories.TransactionRepository;
import pl.coderslab.bookie.service.TransactionService;
import pl.coderslab.bookie.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	UserService userService;
	@Autowired
	TransactionRepository transactionRepo;
	
	@Override
	@Transactional
	public void addTransaction(Transaction transaction) throws Exception {
		User user = userService.getCurrentUser();
		transaction.setUser(user);
		
	
		//if it is withdrawal request we're checking if the balance exceed asking amount 
		if(user.getBalance().compareTo(transaction.getAmount())<0 && transaction.getName().equals("withdraw")) {
			throw new Exception();
		}
		// processing withdrawal
		else if(transaction.getName().equals("withdraw")) {

			user.setBalance(user.getBalance().subtract(transaction.getAmount()));
			transactionRepo.save(transaction);
			userService.updateUser(user);
		}// if it is first deposit,and deposit amount is up to 200 we double it and put it all as Bonus balance
		else if(findByUser(user).isEmpty() && transaction.getAmount().compareTo(BigDecimal.valueOf(200.01))<0){
			user.setBonusBalance(transaction.getAmount().multiply(BigDecimal.valueOf(2.0)));
			Transaction bonus = new Transaction("bonus",transaction.getAmount(),user);
			transactionRepo.save(bonus);
			transactionRepo.save(transaction);
			userService.updateUser(user);
		} //if it is first deposit and amount over 200 we double 200 and put it to Bonus Balance and rest to withdrawable balance
		else if(findByUser(user).isEmpty() && transaction.getAmount().compareTo(BigDecimal.valueOf(200.01))>=0) {
			user.setBonusBalance(BigDecimal.valueOf(400.00));
			user.setBalance(transaction.getAmount().subtract(BigDecimal.valueOf(200.00)));
			Transaction bonus = new Transaction("bonus",new BigDecimal(200.00),user);
			transactionRepo.save(bonus);
			transactionRepo.save(transaction);
			userService.updateUser(user);
			
		}//any other case means that it is deposit not affecting bonus balance
		else {
			user.setBalance(user.getBalance().add(transaction.getAmount()));
			transactionRepo.save(transaction);
			userService.updateUser(user);
		}
		
		
	}

	@Override
	public List<Transaction> findByUser(User user) {
		return transactionRepo.findByUser(user);
	}
	
}
