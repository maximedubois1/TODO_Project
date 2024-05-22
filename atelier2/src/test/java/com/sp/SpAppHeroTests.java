package com.sp;

import com.sp.model.dto.AuthDTO;
import com.sp.model.dto.UserDTO;
import com.sp.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class SpAppHeroTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private static AuthService authService;

	@BeforeAll
    static void initData(){
		AuthDTO	authDTO = new AuthDTO();
		authDTO.setSurname("test");
		authDTO.setPassword("admin");
		Cookie cookie = authService.register(authDTO);

	}

	@Test
	void contextLoads() {
		HttpServletRequest request = new MockHttpServletRequest();
        UserDTO userDTO = authService.getLoggedUser(request);

	}

}
