package com.sp.controller;

import com.sp.model.dto.AuthDTO;
import com.sp.service.AuthService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
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

    @Test
    void shouldReturnLogoutSuccessfulWhenLogout() throws Exception {
        String username = "Username";

        when(authService.logout()).thenReturn(new Cookie("Token", null));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getHeader(HttpHeaders.SET_COOKIE))
                .isNotNull().doesNotContain("Token="+username);
        assertThat(mvcResult.getResponse().getContentAsString())
                .isEqualTo("Logout successful");

        verify(authService, times(1)).logout();
    }
    @Test
    void shouldReturnLoginSuccessfulWhenAuth() throws Exception {
        String username = "Username";
        String password = "MyPassword";

        when(authService.authenticate(any(AuthDTO.class))).thenReturn(new Cookie("Token", username));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"surname\": \"" + username + "\", " +
                                 "\"password\": \"" + password + "\" }"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getHeader(HttpHeaders.SET_COOKIE))
                .isNotNull().contains("Token="+username);
        assertThat(mvcResult.getResponse().getContentAsString())
                .isEqualTo("Login successful");

        verify(authService, times(1)).authenticate(any(AuthDTO.class));
    }

    @Test
    void shouldReturnLoginFailedWhenAuthServiceProvidesNoCookie() throws Exception {
        String username = "Username";
        String password = "MyPassword";

        when(authService.authenticate(any(AuthDTO.class))).thenReturn(null);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"surname\": \"" + username + "\", " +
                                 "\"password\": \"" + password + "\" }"))
                .andExpect(status().isUnauthorized())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString())
                .isEqualTo("Login failed");

        verify(authService, times(1)).authenticate(any(AuthDTO.class));
    }




}
