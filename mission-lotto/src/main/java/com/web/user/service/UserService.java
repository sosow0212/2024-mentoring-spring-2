package com.web.user.service;

import com.web.lotto.service.exception.NegativeAmountException;
import com.web.user.domain.entity.User;

import com.web.user.domain.repository.UserRepository;

import com.web.user.service.exception.NotFoundUserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String userName, int balance) {

        validateBalanceException(balance);
        User user = User.builder()
                .userName(userName)
                .lottoCount(0)
                .balance(balance)
                .build();
        return userRepository.save(user);

    }

    public User getUserById(Long userId) {

        return userRepository
                .findById(userId)
                .orElseThrow(NotFoundUserException::new);

    }

    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    private static void validateBalanceException(final int balance) {

        if (balance < 0) {
            throw new NegativeAmountException();
        }

    }
}