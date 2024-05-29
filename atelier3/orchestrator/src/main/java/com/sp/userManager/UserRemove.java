package com.sp.userManager;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Named
@RequiredArgsConstructor
@Log4j2
public class UserRemove implements JavaDelegate {
    private static final String USER_SERVICE_URL = "http://your-user-service-host:port/api/v1/users"; // TODO: find the great url

    @Override
    public void execute(DelegateExecution delegateExecution) throws BpmnError {

        String userid = (String) delegateExecution.getVariable("userId"); // TODO: find a way to get userid

        // Send user data to user service
        new RestTemplate().delete(USER_SERVICE_URL+ userid);

        // Handle response
        log.info("User deleted!");

    }
}
