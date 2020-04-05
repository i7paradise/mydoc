package com.mydoc.rdv.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mydoc.rdv.core.exceptions.InvalidCredentialsException;
import com.mydoc.rdv.core.exceptions.UserNotFoundException;
import com.mydoc.rdv.core.model.User;
import com.mydoc.rdv.core.services.PasswordService.PasswordNotValidException;
import com.mydoc.rdv.core.services.UserService;
import com.mydoc.rdv.web.controller.forms.SubscriptionForm;

@Controller
public class LoginController {

	private final UserService userService;
	private final UserSession userSession;

	@Autowired
	public LoginController(UserService userService, UserSession userSession) {
		this.userService = userService;
		this.userSession = userSession;
	}

	@GetMapping({ "/", "/login", "/index" })
	public String loginForm(ModelMap model) {
		if (userSession.isConnected()) {
			return "secure/search-doc";
		}
		return "login";
	}

	@PostMapping("/authenticate")
	public String authenticate(ModelMap model,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam(value = "rememberMe", required = false) boolean rememberMe) {
		try {
			User user = userService.authenticate(email, password);
			userSession.setUser(user);
			return "secure/search-doc";
 		} catch (UserNotFoundException e) {
			model.addAttribute("errorMsg", "we don't know you");
		} catch (InvalidCredentialsException e) {
			model.addAttribute("errorMsg", "invalid credentials");
		}
		model.addAttribute("email", email);
		return "login";
	}

	@GetMapping("/subscribe")
	public String subscribe() {
		return "subscribe";
	}

	@PostMapping("/subscribe")
	public String subscribeAction(ModelMap model, SubscriptionForm subscriptionForm) {
		User user = User.create()
				.withEmail(subscriptionForm.getEmail())
				.withFirstName(subscriptionForm.getFirstName())
				.withLastName(subscriptionForm.getLastName())
				.build();
		if (user.emailNotValid()) {
			//TODO add email validation
		}
		if (user.nameNotValid()) {
			//TODO add name validation
		}
		try {
			userService.signup(user, subscriptionForm.getPassword());
			return "login";
		} catch (PasswordNotValidException e) {
			return subscribe();
		}
	}

}
