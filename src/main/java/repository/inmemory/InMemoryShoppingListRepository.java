package repository.inmemory;

import model.ShoppingList;
import model.User;
import repository.ShoppingListRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryShoppingListRepository implements ShoppingListRepository {
    private final Map<Long, ShoppingList> repository = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public ShoppingList save(ShoppingList shoppingList) {

        if (shoppingList.getId() == null) {
            shoppingList.setId(idGenerator.getAndIncrement());
        }

        repository.put(shoppingList.getId(), shoppingList);
        return shoppingList;
    }

    @Override
    public Optional<ShoppingList> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public List<ShoppingList> findAllByOwnerId(Long ownerId) {
        return repository.values().stream().
                filter(list -> list.getOwnerId() != null && list.getOwnerId().equals(ownerId))
                .toList();
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.remove(id) != null;
    }
}
