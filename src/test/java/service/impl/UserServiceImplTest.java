package service.impl;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    private UserRepository userRepositoryMock;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {

        userRepositoryMock = mock(UserRepository.class);

        userService = new UserServiceImpl(userRepositoryMock);
    }

    @Test
    void shouldReturnExistingUserWhenTelegramIdExists() {
        Long telegramId = 123456L;
        User existingUser = new User();
        existingUser.setTelegramId(telegramId);

        // НАСТРОЙКА МОКА:
        // Когда у мока вызывают findByTelegramId с аргументом 123456L,
        // верни Optional, содержащий нашего пользователя.
        when(userRepositoryMock.findByTelegramId(telegramId))
                .thenReturn(Optional.of(existingUser));

        User result = userService.getOrCreateUser(telegramId, "test");

        assertNotNull(result);

        verify(userRepositoryMock, never()).save(any(User.class));
    }
}