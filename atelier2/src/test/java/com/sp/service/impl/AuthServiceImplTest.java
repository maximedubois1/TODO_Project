package com.sp.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sp.model.UserEntity;
import com.sp.model.dto.AuthDTO;
import com.sp.repository.UserRepository;
import com.sp.service.CardService;
import com.sp.service.UserService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImplTest.class);
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardService cardService;

    private AuthServiceImpl authService;

    @Test
    public void testRegister() {
        // Initialize dependencies
        userService = Mockito.mock(UserService.class);
        userRepository = Mockito.mock(UserRepository.class);
        cardService = Mockito.mock(CardService.class);
        // Initialize service to test
        authService = new AuthServiceImpl();
        authService.userService = userService;
        authService.userRepository = userRepository;
        authService.cardService = cardService;

        // Define values for the test
        AuthDTO authDTO = new AuthDTO();
        authDTO.setSurname("testSurname");
        authDTO.setPassword("testPassword");

        // Mock responses
        UserEntity user = new UserEntity();
        user.setSurname(authDTO.getSurname());
        user.setName(authDTO.getSurname());
        user.setPassword(authDTO.getPassword());
        Mockito.when(userRepository.findBySurname(authDTO.getSurname())).thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);
        
        // Perform the action
        Cookie result = authService.register(authDTO);
        
        // Verify results
        assertNotNull(result);
        assertEquals(result.getName(), "Token");
        assertEquals(result.getValue(), authDTO.getSurname());
    }

    @Test
    public void testRegister_AlreadyRegisteredUser() {
        // Initialize dependencies
        userService = Mockito.mock(UserService.class);
        userRepository = Mockito.mock(UserRepository.class);
        cardService = Mockito.mock(CardService.class);
        // Initialize service to test
        authService = new AuthServiceImpl();
        authService.userService = userService;
        authService.userRepository = userRepository;
        authService.cardService = cardService;

        // Define values for the test
        AuthDTO authDTO = new AuthDTO();
        authDTO.setSurname("testSurname");
        authDTO.setPassword("testPassword");

        // Mock responses
        UserEntity user = new UserEntity();
        user.setSurname(authDTO.getSurname());
        user.setName(authDTO.getSurname());
        user.setPassword(BCrypt.withDefaults().hashToString(12, authDTO.getPassword().toCharArray()));
        Mockito.when(userRepository.findBySurname(user.getSurname())).thenReturn(user);
        
        // Perform the action
        Cookie result = authService.register(authDTO);
        
        // Verify results
        assertNull(result);
    }
}