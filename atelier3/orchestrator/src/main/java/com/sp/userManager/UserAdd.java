package com.sp.userManager;

import com.sp.dto.AuthDTO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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

    private static final String USER_SERVICE_URL = "http://gateway:8080/api/v1/auth/orch"; // TODO: find the great url

    @Override
    public void execute(DelegateExecution delegateExecution) throws BpmnError {
        // Extract user data from delegateExecution (assuming it's passed as a variable)
        log.info(delegateExecution.toString());
        log.info(delegateExecution.getVariables().toString());
        String username = (String) delegateExecution.getVariable("username");
        String password = (String) delegateExecution.getVariable("password");
        log.info("Adding user: username={}, password={}", username, password);

        // Create user object
        AuthDTO user = new AuthDTO(username, password);
        log.info("User: {}", user);

        // Send user data to user service
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AuthDTO> request = new HttpEntity<>(user);
        log.info("Sending user data to user service: {}", request.getBody());

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(USER_SERVICE_URL, HttpMethod.POST, request, String.class);
        } catch (RestClientException e) {
            log.error("Failed to access to the platform : {}", e.getMessage());
            throw new BpmnError("Failed to access to the platform");
        }



        HttpHeaders headers = response.getHeaders();
        delegateExecution.setVariable("headers", headers); // TODO: need to test to send only auth ticket or userid

        delegateExecution.setVariable("user", user);

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
