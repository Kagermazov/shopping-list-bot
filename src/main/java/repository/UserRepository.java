package repository;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User newUser);
    Optional<User> findById(Long id);
    List<User> findAll();
    boolean deleteById(Long id);
    Optional<User> findByTelegramId(Long telegramId);
}
