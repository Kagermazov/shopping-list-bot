package service;

import model.User;

import java.util.Optional;

public interface UserService {

    User getOrCreateUser(Long telegramId, String username);

    Optional<User> findById(Long id);

    Optional<User> findByTelegramId(Long telegramId);
}
