package com.sp.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class CardControllerIT {

    private static final Logger log = LoggerFactory.getLogger(CardControllerIT.class);
    private static final String BASE_URL = "/api/v1/cards";
    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
    @Autowired
    private MockMvc mockMvc;

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

    @Test
    public void getAll_shouldReturnAllCards() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].name").value("card1"))
                .andExpect(jsonPath("$[1].name").value("card2"));
    }

    @Test
    public void getCardsByUser_shouldReturnUserCards() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("card1"))
                .andExpect(jsonPath("$[1].name").value("card2"));
    }

    @Test
    public void getCardsOnMarket_shouldReturnAvailableCards() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/market"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("card4"));
    }

    @Test
    public void getCardById_shouldReturnCardById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("card1"));
    }

    @Test
    public void isSellable_shouldReturnTrue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/is-sellable/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    public void isSellable_shouldReturnFalse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/is-sellable/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    public void isBuyable_shouldReturnTrue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/is-buyable/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    public void isBuyable_shouldReturnFalse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/is-buyable/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    public void addCardToUser_shouldAddCardToUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/buy/4/to-user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Card added to user"));
    }

    @Test
    public void removeCardFromUser_shouldRemoveCardFromUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/sell/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Card removed from user"));

        // now expect that card 2 has no owner, so it should be buyable
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/is-buyable/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

    }

    @Test
    public void generateFiveCardsForUser_shouldGenerateFiveCardsForUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/generate-for/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(5));
    }

}
