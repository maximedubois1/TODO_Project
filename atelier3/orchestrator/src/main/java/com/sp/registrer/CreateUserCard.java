package com.sp.registrer;

import com.sp.dto.AuthDTO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Named
@RequiredArgsConstructor
@Log4j2
public class CreateUserCard implements JavaDelegate {
    private static final String USER_SERVICE_URL = "http://your-user-service-host:port/generate-for/"; // Replace with actual URL

    @Override
    @SneakyThrows
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // Extract user data from delegateExecution (assuming it's passed as a variable)

        // Create user object

        // Send user data to user service
        String response = new RestTemplate().postForObject(USER_SERVICE_URL + "42", "", String.class);

        // Handle response (success/failure)
        assert response != null;
        if (response.equals("success")) {
            log.info("Card created successfully!");
        } else {
            log.error("Failed to create card: {}", response);
            throw new ProcessEngineException("Failed to create card");
        }
    }
}
