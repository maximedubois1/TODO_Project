package com.sp.register;

import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Named
public class UserRemove implements JavaDelegate {
    private static final String USER_SERVICE_URL = "http://your-user-service-host:port/api/v1/users"; // Replace with actual URL

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


        // Send user data to user service
        new RestTemplate().delete(USER_SERVICE_URL);

        // Handle response (success/failure)

        System.out.println("User deleted!");

    }
}
