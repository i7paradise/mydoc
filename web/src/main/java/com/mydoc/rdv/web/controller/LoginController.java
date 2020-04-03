package com.mydoc.rdv.web.controller;

import com.mydoc.rdv.core.exceptions.InvalidCredentialsException;
import com.mydoc.rdv.core.exceptions.UserNotFoundException;
import com.mydoc.rdv.core.model.User;
import com.mydoc.rdv.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
public class LoginController {

	private static final String SESSION_ATT_USER = "user";
	private final UserService userService;

	@Autowired
	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping({ "/", "/login", "/index" })
	public String loginForm(ModelMap model) {
		if (isConnected()) {
			model.addAttribute(SESSION_ATT_USER, getUser());
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
			model.addAttribute(SESSION_ATT_USER, user);
			return "secure/search-doc";
		} catch (UserNotFoundException e) {
			model.addAttribute("errorMsg", "we don't know you");
		} catch (InvalidCredentialsException e) {
			model.addAttribute("errorMsg", "invalid credentials");
		}
		return "login";
	}

	User getUser() {
		return (User) RequestContextHolder.currentRequestAttributes().getAttribute(SESSION_ATT_USER,
				RequestAttributes.SCOPE_SESSION);
	}

	boolean isConnected() {
		return getUser() != null;
	}

}
