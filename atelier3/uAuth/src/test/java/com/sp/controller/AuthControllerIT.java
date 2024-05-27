package com.sp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.model.dto.AuthDTO;
import com.sp.service.impl.UserDetailsServiceImpl;
import com.sp.utils.security.JwtService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(scripts = "classpath:sql/insert_data_before_tests.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:sql/delete_data_and_init_seq.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@ActiveProfiles("test")
public class AuthControllerIT {


    private static final Logger log = LoggerFactory.getLogger(AuthControllerIT.class);
    private static final String BASE_URL = "/api/v1/auth";

    ;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public Cookie createAuthCookie() {
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("User");
        String jwt = jwtService.generateToken(userDetails.getUsername());
        return new Cookie("auth_jwt", jwt);
    }

    @Test
    public void testLogin() throws Exception {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setSurname("User");
        authDTO.setPassword("password");

        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/login")
                        .contentType("application/json")
                        .content(
                                objectMapper.writeValueAsString(authDTO)))
                .andExpect(status().isOk())
                .andReturn();
        log.info(result.getResponse().getContentAsString());
        boolean cookieExists = Arrays.stream(result.getResponse().getCookies())
                .anyMatch(cookie -> cookie.getName().equals("auth_jwt"));

        assertTrue(cookieExists);

    }

    @Test
    public void register() throws Exception {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setSurname("User3");
        authDTO.setPassword("password");

        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/register")
                        .contentType("application/json")
                        .content(
                                objectMapper.writeValueAsString(authDTO)))
                .andExpect(status().isOk())
                .andReturn();

        log.info(result.getResponse().getContentAsString());

    }
}