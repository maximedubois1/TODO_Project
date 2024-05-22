package com.sp.controller;

import com.sp.model.dto.AuthDTO;
import com.sp.service.AuthService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    void shouldReturnRegistrationSuccessfulWhenRegister() throws Exception {
        String username = "Username";
        String password = "MyPassword";

        AuthDTO authDTO = new AuthDTO();
        authDTO.setSurname(username);
        authDTO.setPassword(password);

        when(authService.register(any(AuthDTO.class))).thenReturn(new Cookie("Token", username));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"surname\": \"" + username + "\", " +
                                 "\"password\": \"" + password + "\" }"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getHeader(HttpHeaders.SET_COOKIE))
                .isNotNull().contains("Token="+username);
        assertThat(mvcResult.getResponse().getContentAsString())
                .isEqualTo("Registration successful");

        verify(authService, times(1)).register(any(AuthDTO.class));
    }
    
    @Test
    void shouldReturnRegistrationFailedWhenRegister() throws Exception {
        String username = "Username";
        String password = "MyPassword";

        AuthDTO authDTO = new AuthDTO();
        authDTO.setSurname(username);
        authDTO.setPassword(password);

        when(authService.register(any(AuthDTO.class))).thenReturn(null);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"surname\": \"" + username + "\", " +
                                 "\"password\": \"" + password + "\" }"))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString())
                .isEqualTo("Registration failed");

        verify(authService, times(1)).register(any(AuthDTO.class));
    }
}