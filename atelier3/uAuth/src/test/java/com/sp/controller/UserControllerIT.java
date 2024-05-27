package com.sp.controller;

import com.sp.service.impl.UserDetailsServiceImpl;
import com.sp.utils.security.JwtService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(scripts = "classpath:sql/insert_data_before_tests.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:sql/delete_data_and_init_seq.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@ActiveProfiles("test")
public class UserControllerIT {


    private static final Logger log = LoggerFactory.getLogger(UserControllerIT.class);
    private static final String BASE_URL = "/api/v1/users";

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @BeforeAll
    public static void setUp() {
        log.info("Setting up tests");
        postgresContainer.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @AfterAll
    public static void tearDown() {
        log.info("Tearing down tests");
        postgresContainer.stop();
    }

    public Cookie createAuthCookie() {
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("User");
        String jwt = jwtService.generateToken(userDetails.getUsername());
        return new Cookie("auth_jwt", jwt);
    }

    @Test
    public void getAll_shouldReturnAllUsers() throws Exception {
        Cookie authCookie = createAuthCookie();

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .cookie(authCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].surname").value("User"))
                .andExpect(jsonPath("$[1].surname").value("User2"));
    }

    @Test
    public void getById_shouldReturnUser() throws Exception {
        Cookie authCookie = createAuthCookie();
        String userId = "1";

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + userId)
                        .cookie(authCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId));
    }

    @Test
    public void getProfile_shouldReturnProfile() throws Exception {
        Cookie authCookie = createAuthCookie();

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/profile")
                        .cookie(authCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname").value("User"));
    }

    @Test
    public void testWallet_shouldReturnTrue() throws Exception {
        Cookie authCookie = createAuthCookie();
        String userId = "1";
        String amount = "500";

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/wallet/" + userId + "/test/" + amount)
                        .cookie(authCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    public void addWallet_shouldReturnTrue() throws Exception {
        Cookie authCookie = createAuthCookie();
        String userId = "1";
        String amount = "500";

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/wallet/" + userId + "/add/" + amount)
                        .cookie(authCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    public void subWallet_shouldReturnTrue() throws Exception {
        Cookie authCookie = createAuthCookie();
        String userId = "1";
        String amount = "500";

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/wallet/" + userId + "/sub/" + amount)
                        .cookie(authCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    public void delete_shouldReturnNoContent() throws Exception {
        Cookie authCookie = createAuthCookie();
        String userId = "1";

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + userId)
                        .cookie(authCookie))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getById_shouldReturnNotFound() throws Exception {
        Cookie authCookie = createAuthCookie();
        String userId = "9999"; // Un ID qui n'existe pas dans la base de données

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + userId)
                        .cookie(authCookie))
                .andExpect(status().isNotFound());
    }
}
