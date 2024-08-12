package com.lotto.service;

import com.lotto.dto.request.RegisterRequest;
import com.lotto.entity.User;
import com.lotto.exception.NotExistUserNameException;
import com.lotto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterRequest registerRequest) {
        User user = User.builder()
                .userName(registerRequest.name())
                .money(registerRequest.money())
                .build();
        userRepository.save(user);
    }

    public User getUserByName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(NotExistUserNameException::new);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
