package se.jensen.grupp9.socialpostsapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.jensen.grupp9.socialpostsapp.dto.UserRegistrationDTO;
import se.jensen.grupp9.socialpostsapp.logging.AppLogger;
import se.jensen.grupp9.socialpostsapp.model.User;
import se.jensen.grupp9.socialpostsapp.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * {@link UserServiceTest} innehåller enhetstester för {@link UserService}.
 * <p>
 * Klassen använder Mockito för att mocka beroenden och verifiera beteendet hos
 * {@link UserService} utan att behöva ansluta till en riktig databas.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AppLogger appLogger;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_success() throws Exception {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("test");
        dto.setPassword("password");

        when(userRepository.existsByUsername("test")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("hashed_password");

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User u = invocation.getArgument(0);

                    // Set ID for testing
                    java.lang.reflect.Field idField = User.class.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(u, 1L);

                    return u;
                });

        User user = userService.createUser(dto);

        assertEquals("test", user.getUsername());
    }
}