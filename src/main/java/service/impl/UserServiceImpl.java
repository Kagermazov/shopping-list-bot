package service.impl;

import model.User;
import repository.UserRepository;
import service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getOrCreateUser(Long telegramId, String username) {
        return userRepository.findByTelegramId(telegramId)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setTelegramId(telegramId);
                    newUser.setUsername(username);
                    return userRepository.save(newUser);
                });
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByTelegramId(Long telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }
}