package com.mydoc.rdv.core.services.impl;

import com.mydoc.rdv.core.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl sut;

    @Test
    public void addUserTest() {
        sut.addNew(User.create()
                .withEmail("wili@test.fr")
                .withFirstName("wili")
                .withLastName("wanka")
                .build());
    }

}
