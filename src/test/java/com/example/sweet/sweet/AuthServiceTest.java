package com.example.sweet.sweet;

import com.example.sweet.sweet.config.JwtUtil;
import com.example.sweet.sweet.model.user;
import com.example.sweet.sweet.repository.UserRepository;
import com.example.sweet.sweet.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authManager;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode("1234")).thenReturn("encodedPass");

        User user = User.builder()
                .username("John")
                .email(email)
                .password("encodedPass")
                .roles(Set.of("USER"))
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = authService.register("John", email, "1234");

        assertEquals(email, saved.getEmail());
        assertEquals("encodedPass", saved.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterDuplicateEmail() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class,
                () -> authService.register("John", email, "1234"));
    }

    @Test
    void testLoginSuccess() {
        String email = "test@example.com";
        String token = "jwtToken";

        when(jwtUtil.generateToken(email)).thenReturn(token);

        String result = authService.login(email, "password");

        assertEquals(token, result);
    }
}
