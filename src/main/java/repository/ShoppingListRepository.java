package repository;

import model.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository {
    ShoppingList save(ShoppingList shoppingList);

    Optional<ShoppingList> findById(Long id);

    List<ShoppingList> findAllByOwnerId(Long ownerId);

    boolean deleteById(Long id);
}