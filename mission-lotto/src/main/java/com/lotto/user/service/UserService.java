package com.lotto.user.service;

import com.lotto.user.service.dto.CreateUserRequest;
import com.lotto.user.domain.entity.User;
import com.lotto.user.domain.repository.UserRepository;

import com.lotto.user.service.exception.NegativeAmountException;

import com.lotto.user.service.exception.NotFoundUserException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserRequest registerUser(String userName, int balance) {
        validateBalanceException(balance);
        User user = User
                .builder()
                .userName(userName)
                .lottoCount(0)
                .winning(0)
                .balance(balance)
                .build();

        User savedUser =userRepository.save(user);

        return new CreateUserRequest(
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getBalance(),
                savedUser.getLottoCount()
        );
    }

    public void buyLotto(Long userId, int ticketCount){
        User user =findUser(userId);


    }

    @Transactional()
    public User findUser(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(NotFoundUserException::new);
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