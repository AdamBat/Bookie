package pl.coderslab.bookie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import pl.coderslab.bookie.entities.Role;
import pl.coderslab.bookie.entities.User;
import pl.coderslab.bookie.repositories.RoleRepository;
import pl.coderslab.bookie.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return user;
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		Role role = roleRepo.findByName("ROLE_USER");
		user.setRole(role);
		userRepo.save(user);
	}
	@Override
	public void saveAdmin(User user) {
		user.setVerified(true);
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		Role role = roleRepo.findByName("ROlE_ADMIN");
		user.setRole(role);
		userRepo.save(user);
	}

}
