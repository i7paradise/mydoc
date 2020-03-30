package com.mydoc.rdv.core.services.impl;


import com.mydoc.rdv.core.model.User;
import com.mydoc.rdv.core.services.UserService;
import com.mydoc.rdv.persistence.model.Ta_User;
import com.mydoc.rdv.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addNew(User user) {
        Ta_User taUser = new Ta_User();
        taUser.setEmail(user.getEmail());
        taUser.setFirstName(user.getFirstName());
        taUser.setLastName(user.getLastName());
        userRepository.save(taUser);
    }
}
