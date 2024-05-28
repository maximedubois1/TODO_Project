package com.sp.userManager;

import com.sp.dto.AuthDTO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Named
@RequiredArgsConstructor
@Log4j2
public class UserAdd implements JavaDelegate {

    private static final String USER_SERVICE_URL = "http://gateway:8080/api/v1/register"; // TODO: find the great url

    @Override
    public void execute(DelegateExecution delegateExecution) throws BpmnError {
        // Extract user data from delegateExecution (assuming it's passed as a variable)
        String username = (String) delegateExecution.getVariable("username");
        String password = (String) delegateExecution.getVariable("password");

        // Create user object
        AuthDTO user = new AuthDTO(username, password);

        // Send user data to user service
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AuthDTO> request = new HttpEntity<>(user);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(USER_SERVICE_URL, HttpMethod.POST, request, String.class);
        } catch (RestClientException e) {
            log.error("Failed to access to the platform");
            throw new BpmnError("Failed to access to the platform");
        }



        HttpHeaders headers = response.getHeaders();
        delegateExecution.setVariable("headers", headers); // TODO: need to test to send only auth ticket or userid


        // Handle response (success/failure)
        String responseBody = response.getBody();
        if ("Login successful".equals(responseBody)) {
            log.info("User added successfully!");
        } else {
            log.error("Failed to add user: {}",response);
            throw new BpmnError("Failed to add user");
        }
    }
}
