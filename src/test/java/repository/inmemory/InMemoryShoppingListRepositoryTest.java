package repository.inmemory;

import model.ShoppingItem;
import model.ShoppingList;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ShoppingListRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryShoppingListRepositoryTest {

    private ShoppingListRepository shoppingListRepository;

    @BeforeEach
    void setUp() {
        shoppingListRepository = new InMemoryShoppingListRepository();
    }

    @Test
    void shouldSaveNewShoppingList() {
        ShoppingList newShoppingList = new ShoppingList();

        shoppingListRepository.save(newShoppingList);

        assertNotNull(newShoppingList.getId());

        Optional<ShoppingList> foundShoppingList = shoppingListRepository.findById(newShoppingList.getId());

        assertTrue(foundShoppingList.isPresent());
        assertEquals(newShoppingList, foundShoppingList.get());
    }

    @Test
    void shouldUpdateExistingShoppingList() {
        ShoppingList newShoppingList = new ShoppingList();
        newShoppingList.setTitle("Title");
        shoppingListRepository.save(newShoppingList);

        ShoppingList secondShoppingList = new ShoppingList();
        secondShoppingList.setId(newShoppingList.getId());

        assertTrue(shoppingListRepository.findById(secondShoppingList.getId()).isPresent());
        assertEquals(shoppingListRepository.findById(secondShoppingList.getId()).get().getId(), newShoppingList.getId());
        assertEquals(shoppingListRepository.findById(secondShoppingList.getId()).get().getTitle(), "Title");
    }

    @Test
    void shouldReturnShoppingListWhenShoppingListExists() {

    }

    @Test
    void shouldReturnEmptyOptionalWhenShoppingListDoesNotExist() {

    }

    @Test
    void shouldReturnAllListsByUserId() {

    }

    @Test
    void shouldReturnEmptyListWhenRepositoryIsEmpty() {

    }

    @Test
    void shouldReturnEmptyListWhenUserHasNoShoppingLists() {

    }

    @Test
    void shouldReturnTrueAndRemoveShoppingListWhenShoppingListExists() {

    }

    @Test
    void shouldReturnFalseWhenDeleteNonExistentShoppingList() {

    }
}