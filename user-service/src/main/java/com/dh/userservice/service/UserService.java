package com.dh.userservice.service;

import com.dh.userservice.model.SubscriptionDTO;
import com.dh.userservice.model.User;
import com.dh.userservice.model.UserDTO;
import com.dh.userservice.repository.KeycloakRepository;
import com.dh.userservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final List<User> userRepository;
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private KeycloakRepository keycloakRepository;

    public UserService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = List.of(new User("1a", "Tomas", "Pereyra", "tomas.pereyra@digitalhouse.com"));
    }

    public User findById(Integer id){
        User user = userRepository.stream().filter(_user -> Objects.equals(_user.getId(), id)).findFirst().orElse(null);
        SubscriptionDTO subscriptionDTO = subscriptionRepository.findByUserId(id);
        if (user != null)
            user.setSubscription(subscriptionDTO);

        return user;
    }

    public User findByUsername(String username){
        return keycloakRepository.findByUsername(username);
    }

    public List<UserDTO> findAllNonAdminUsers(){
        return keycloakRepository.findAll();
    }

}
