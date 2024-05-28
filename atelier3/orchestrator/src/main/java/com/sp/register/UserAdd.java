package com.sp.register;

import com.sp.dto.AuthDTO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.client.RestTemplate;

@Component
@Named
@RequiredArgsConstructor
@Log4j2
public class UserAdd implements JavaDelegate {

    private static final String USER_SERVICE_URL = "http://auth-service/api/v1/register"; // Replace with actual URL

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // Extract user data from delegateExecution (assuming it's passed as a variable)
        String username = (String) delegateExecution.getVariable("username");
        String password = (String) delegateExecution.getVariable("password");

        // Create user object
        AuthDTO user = new AuthDTO(username, password);

        // Send user data to user service
        String response = new RestTemplate().postForObject(USER_SERVICE_URL, user, String.class);

        // Handle response (success/failure)
        assert response != null;
        if (response.equals("success")) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user: {}");
            throw new ProcessEngineException("Failed to add user");
        }
    }
}
