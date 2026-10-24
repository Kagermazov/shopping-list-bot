package repository.inmemory;

import model.ShoppingItem;
import model.ShoppingList;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ShoppingListRepository;

import java.util.List;
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
        ShoppingList firstShoppingList = new ShoppingList();
        firstShoppingList.setId(1L);
        shoppingListRepository.save(firstShoppingList);

        ShoppingList secondShoppingList = new ShoppingList();
        secondShoppingList.setId(1L);
        shoppingListRepository.save(secondShoppingList);

        assertTrue(shoppingListRepository.findById(1L).isPresent());
        assertEquals(firstShoppingList, shoppingListRepository.findById(1L).get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenShoppingListDoesNotExist() {
        ShoppingList myShoppingList = new ShoppingList();
        myShoppingList.setId(1L);

        assertTrue(shoppingListRepository.findById(myShoppingList.getId()).isEmpty());
    }

    @Test
    void shouldReturnAllListsByUserId() {
        ShoppingList firstShoppingList = new ShoppingList();
        firstShoppingList.setOwnerId(1L);
        shoppingListRepository.save(firstShoppingList);

        ShoppingList secondShoppingList = new ShoppingList();
        secondShoppingList.setOwnerId(1L);
        shoppingListRepository.save(secondShoppingList);

        assertInstanceOf(List.class, shoppingListRepository.findAllByOwnerId(1L));
        ShoppingList firstExtractedShoppingList = shoppingListRepository.findAllByOwnerId(1L).get(0);
        ShoppingList secondExtractedShoppingList = shoppingListRepository.findAllByOwnerId(1L).get(1);

        assertEquals(1L, firstExtractedShoppingList.getId());
        assertEquals(2L, secondExtractedShoppingList.getId());
    }

    @Test
    void shouldReturnEmptyListWhenRepositoryIsEmpty() {
        assertTrue(shoppingListRepository.findById(1L).isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenUserHasNoShoppingLists() {
        assertTrue(shoppingListRepository.findAllByOwnerId(1L).isEmpty());
    }

    @Test
    void shouldReturnTrueAndRemoveShoppingListWhenShoppingListExists() {
        ShoppingList firstShoppingList = new ShoppingList();
        firstShoppingList.setOwnerId(1L);
        shoppingListRepository.save(firstShoppingList);

        ShoppingList secondShoppingList = new ShoppingList();
        secondShoppingList.setOwnerId(1L);

        assertTrue(shoppingListRepository.deleteById(1L));
        assertFalse(shoppingListRepository.findById(1L).isPresent());
    }

    @Test
    void shouldReturnFalseWhenDeleteNonExistentShoppingList() {
        assertFalse(shoppingListRepository.deleteById(1L));
    }
}