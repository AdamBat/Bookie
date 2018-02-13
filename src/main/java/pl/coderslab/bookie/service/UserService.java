package pl.coderslab.bookie.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import pl.coderslab.bookie.entities.User;


public interface UserService extends UserDetailsService {
	User findByUsername(String username);
	void saveUser(User user);
	void saveAdmin(User user);
	User getCurrentUser();
	List<User> getAll();
	
}
