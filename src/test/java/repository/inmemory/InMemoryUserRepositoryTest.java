package repository.inmemory;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
    }

    @Test
    void shouldSaveNewUser() {
        User newUser = new User();
        newUser.setId(1L);

        userRepository.save(newUser);

        Optional<User> foundUser = userRepository.findById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals(newUser, foundUser.get());
    }

    @Test
    void shouldUpdateExistingUser() {
        User firstUser = new User();
        firstUser.setId(1L);
        firstUser.setFirstName("Ivan");

        User secondUser = new User();
        secondUser.setId(1L);
        secondUser.setFirstName("Peter");

        userRepository.save(firstUser);
        userRepository.save(secondUser);

        Optional<User> foundUser = userRepository.findById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals("Peter", foundUser.get().getFirstName());
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void shouldReturnUserWhenUserExists() {
        User firstUser = new User();
        firstUser.setId(1L);
        firstUser.setFirstName("Ivan");

        userRepository.save(firstUser);

        assertTrue(userRepository.findById(1L).isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenUserDoesNotExist() {
        Optional<User> foundUser = userRepository.findById(999L);
        assertTrue(foundUser.isEmpty());
    }

    @Test
    void shouldReturnAllUsers() {
        User firstUser = new User();
        firstUser.setId(1L);
        User secondUser = new User();
        secondUser.setId(2L);

        userRepository.save(firstUser);
        userRepository.save(secondUser);

        List<User> users = userRepository.findAll();
        assertEquals(2, users.size());
    }

    @Test
    void shouldReturnEmptyListWhenRepositoryIsEmpty() {
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @Test
    void shouldReturnTrueAndRemoveUserWhenUserExists() {
        User firstUser = new User();
        firstUser.setId(1L);
        userRepository.save(firstUser);

        boolean isDeleted = userRepository.deleteById(1L);

        assertTrue(isDeleted);
        assertTrue(userRepository.findById(1L).isEmpty());
    }

    @Test
    void shouldReturnFalseWhenDeleteNonExistentUser() {
        boolean isDeleted = userRepository.deleteById(999L);
        assertFalse(isDeleted);
    }

    @Test
    void shouldFindUserByTelegramId() {
        User user = new User();
        user.setTelegramId(123456789L);
        user.setUsername("test_user");
        userRepository.save(user);

        Optional<User> found = userRepository.findByTelegramId(123456789L);

        assertTrue(found.isPresent());
        assertEquals("test_user", found.get().getUsername());
    }

    @Test
    void shouldReturnEmptyOptionalWhenTelegramIdNotFound() {
        Optional<User> found = userRepository.findByTelegramId(999L);
        assertTrue(found.isEmpty());
    }
}