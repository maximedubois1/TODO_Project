package com.sp.controller;

import com.sp.model.dto.CardDTO;
import com.sp.service.CardService;
import com.sp.utils.TestsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAll_shouldReturnAllCards() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].name").value("card1"))
                .andExpect(jsonPath("$[1].name").value("card2"));
    }

//    @Test
//    public void getById_shouldReturnClientById() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.firstname").value("John"))
//                .andExpect(jsonPath("$.email").value("test1@example.com"));
//    }


}
