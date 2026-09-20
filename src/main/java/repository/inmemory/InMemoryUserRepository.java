package repository.inmemory;

import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, User> repository = new ConcurrentHashMap<>();

    @Override
    public User save(User newUser) {
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
}
