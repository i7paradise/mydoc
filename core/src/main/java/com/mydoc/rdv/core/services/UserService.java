package com.mydoc.rdv.core.services;

import static org.springframework.util.StringUtils.quote;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoc.rdv.core.exceptions.InvalidCredentialsException;
import com.mydoc.rdv.core.exceptions.UserNotFoundException;
import com.mydoc.rdv.core.model.User;
import com.mydoc.rdv.core.services.PasswordService.PasswordNotValidException;
import com.mydoc.rdv.persistence.model.Ta_User;
import com.mydoc.rdv.persistence.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordService passwordService;

	@Autowired
	public UserService(UserRepository userRepository, PasswordService passwordService) {
		this.userRepository = userRepository;
		this.passwordService = passwordService;
	}

	public void signup(User user, String password) throws PasswordNotValidException {
		passwordService.checkPassword(password);
		Ta_User taUser = new Ta_User();
		taUser.setEmail(user.getEmail());
		taUser.setFirstName(user.getFirstName());
		taUser.setLastName(user.getLastName());
		taUser.setPassword(password);
		userRepository.save(taUser);
	}

	public User getUser(String email) throws UserNotFoundException {
		return userRepository.findByEmail(email).map(this::map)
				.orElseThrow(() -> new UserNotFoundException("email = " + quote(email)));
	}

	private User map(Ta_User ta_user) {
		return User.create().withIdUser(ta_user.getIdUser()).withFirstName(ta_user.getFirstName())
				.withLastName(ta_user.getLastName()).withEmail(ta_user.getEmail())
				.withLastConnection(ta_user.getLastConnection()).build();
	}

	public User authenticate(String email, String password) throws UserNotFoundException, InvalidCredentialsException {
		Ta_User taUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("email = " + quote(email)));
		if (Objects.equals(password, taUser.getPassword())) {
			return map(taUser);
		}
		throw new InvalidCredentialsException(email);
	}
}
