package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CustomRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserServiceimpl implements UserService {

	private final UserRepository userrepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final CustomRepository customRepository;
	@Autowired
	private EmailService emailService;
	

	public UserServiceimpl(UserRepository userrepository, BCryptPasswordEncoder passwordEncoder,
	    RoleRepository roleRepository, CustomRepository customRepository) {
	    this.userrepository = userrepository;
	    this.passwordEncoder = passwordEncoder;
	    this.roleRepository = roleRepository;
	    this.customRepository = customRepository;
	}
	

	@Override
	public List<User> findAll() {

		return userrepository.findAll();
	}

	@Override
	public User findById(int theId) {

		User theUser = null;

		Optional<User> result = userrepository.findById(theId);

		if (result.isPresent()) {
			theUser = result.get();
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		String encryptedPassword = passwordEncoder.encode(theUser.getPassword());

		theUser.setPassword(encryptedPassword);

		Role userRole = roleRepository.findByName("USER")
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

		theUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		userrepository.save(theUser);
	}

	@Override
	public void deleteById(int theId) {
		userrepository.deleteById(theId);
	}

	@Override
	public User findByUserName(String userName) {

		return userrepository.findByUsername(userName);
	}

	@Override
	public User findByUserId(String username) {

		return userrepository.findByUsername(username);
	}

	@Override
	public void detach(User user) {

		customRepository.detach(user);
	}
	public void resetPassword(String email) throws EmailNotFoundException, MessagingException {
		User user = userrepository.findByEmail(email);
		if (user == null) {
			throw new EmailNotFoundException("No account found with email: " + email);
		}
		
		String newPassword = generateRandomPassword();
		String encryptedPassword = passwordEncoder.encode(newPassword);
		
		emailService.sendNewPasswordEmail(user.getUsername(), newPassword, user.getEmail());
		
		user.setPassword(encryptedPassword);
		userrepository.save(user);
	}

	
	private String generateRandomPassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder password = new StringBuilder();
		Random rnd = new Random();
		while (password.length() < 6) {
			int index = (int) (rnd.nextFloat() * characters.length());
			password.append(characters.charAt(index));
		}
		return password.toString();
	}
}