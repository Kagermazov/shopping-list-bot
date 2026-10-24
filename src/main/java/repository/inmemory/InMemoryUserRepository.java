package repository.inmemory;

import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, User> repository = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public User save(User newUser) {
        if (newUser.getId() == null) {
            newUser.setId(idGenerator.incrementAndGet());
        }
        repository.put(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.remove(id) != null;
    }

    @Override
    public Optional<User> findByTelegramId(Long telegramId) {
        return repository.values().stream()
                .filter(user -> user.getTelegramId().equals(telegramId))
                .findFirst();
    }
}
