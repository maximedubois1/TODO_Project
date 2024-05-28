package com.sp.register;

import jakarta.inject.Named;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Named
public class CreateUserCard implements JavaDelegate {
    private static final String USER_SERVICE_URL = "http://your-user-service-host:port/generate-for/"; // Replace with actual URL

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // Extract user data from delegateExecution (assuming it's passed as a variable)

        // Create user object

        // Send user data to user service
        String response = new RestTemplate().postForObject(USER_SERVICE_URL + "42", "", String.class);

        // Handle response (success/failure)
        assert response != null;
        if (response.equals("success")) {
            System.out.println("Card created successfully!");
        } else {
            System.out.println("Failed to create card: {}");
            throw new ProcessEngineException("Failed to create card");
        }
    }
}
