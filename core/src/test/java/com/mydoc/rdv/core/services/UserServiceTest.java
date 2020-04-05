package com.mydoc.rdv.core.services;

import com.mydoc.rdv.core.model.User;
import com.mydoc.rdv.core.services.PasswordService.PasswordNotValidException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

	private static final String EMAIL = "wili@test.fr";
	private static final String FIRST_NAME = "wili";
	
    @Autowired
    UserService sut;

    @Test
    public void should_subscribe_user() throws Exception {
        sut.signup(user(), "1234");
        
        assertThat(sut.getUser(EMAIL).getFirstName()).isEqualTo(FIRST_NAME);
    }

	private User user() {
		return User.create()
                .withEmail(EMAIL)
                .withFirstName(FIRST_NAME)
                .withLastName("wanka")
                .build();
	}
    
    @Test
    public void should_fail_subscription_for_non_valid_password() {
    	User user = user();
    	String nonValidPassword = "";
    	assertThrows(PasswordNotValidException.class, () -> {
    		sut.signup(user, nonValidPassword);
    	});
    }

}