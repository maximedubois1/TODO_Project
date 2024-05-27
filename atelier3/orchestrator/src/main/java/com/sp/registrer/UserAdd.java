package com.sp.registrer;

import com.sp.dto.AuthDTO;
import jakarta.inject.Named;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.*;
import org.springframework.web.client.RestTemplate;

@Component
@Named
@RequiredArgsConstructor
@Log4j2
public class UserAdd implements JavaDelegate {
    private static final String USER_SERVICE_URL = "http://your-user-service-host:port/users"; // Replace with actual URL

    @Override
    @SneakyThrows
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // Extract user data from delegateExecution (assuming it's passed as a variable)
        String username = (String) delegateExecution.getVariable("username");
        String email = (String) delegateExecution.getVariable("email");

        // Create user object
        AuthDTO user = new AuthDTO(username, email);

        // Send user data to user service
        String response = new RestTemplate().postForObject(USER_SERVICE_URL, user, String.class);

        // Handle response (success/failure)
        assert response != null;
        if (response.equals("success")) {
            log.info("User added successfully!");
        } else {
            log.error("Failed to add user: {}", response);
            throw new ProcessEngineException("Failed to add user");
        }
    }
}
