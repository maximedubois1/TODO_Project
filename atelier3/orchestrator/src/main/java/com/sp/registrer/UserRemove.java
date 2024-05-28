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
public class UserRemove implements JavaDelegate {
    private static final String USER_SERVICE_URL = "http://your-user-service-host:port/api/v1/users"; // Replace with actual URL

    @Override
    @SneakyThrows
    public void execute(DelegateExecution delegateExecution) throws Exception {


        // Send user data to user service
        new RestTemplate().delete(USER_SERVICE_URL);

        // Handle response (success/failure)

        log.info("User deleted!");

    }
}
