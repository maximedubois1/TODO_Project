package com.sp.userManager;

import com.sp.dto.AuthDTO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.*;

@Component
@Named
@RequiredArgsConstructor
@Log4j2
public class UserAdd implements JavaDelegate {

    private static final String USER_SERVICE_URL = "http://gateway:8080/api/v1/register"; // Replace with actual URL

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // Extract user data from delegateExecution (assuming it's passed as a variable)
        String username = (String) delegateExecution.getVariable("username");
        String password = (String) delegateExecution.getVariable("password");

        // Create user object
        AuthDTO user = new AuthDTO(username, password);

        // Send user data to user service
        //String response = new RestTemplate().postForObject(USER_SERVICE_URL, user, String.class);
        String response = "success";
        log.info("The username {}  with the password {}",username,password);

        // Handle response (success/failure)
        assert response != null;
        if (response.equals("success")) {
            log.info("User added successfully!");
        } else {
            log.error("Failed to add user: {}",response);
            throw new ProcessEngineException("Failed to add user");
        }
    }
}
