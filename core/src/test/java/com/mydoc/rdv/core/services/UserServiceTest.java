package com.mydoc.rdv.core.services;

import com.mydoc.rdv.core.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService sut;

    @Test
    public void addUserTest() throws Exception {
        sut.addNew(User.create()
                .withEmail("wili@test.fr")
                .withFirstName("wili")
                .withLastName("wanka")
                .build());
        assertThat(sut.getUser("jamesbond@test.com").getFirstName()).isEqualTo("james");
    }

}
