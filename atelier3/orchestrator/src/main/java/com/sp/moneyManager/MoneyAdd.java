package com.sp.moneyManager;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@Named
@RequiredArgsConstructor
@Log4j2
public class MoneyAdd implements JavaDelegate {
    private static final String USER_SERVICE_URL = "http://your-user-service-host:port/wallet/"; // TODO: find the great url

    @Override
    public void execute(DelegateExecution delegateExecution) throws BpmnError {
        // Extract user data from delegateExecution (assuming it's passed as a variable)
        String userid = (String) delegateExecution.getVariable("userId"); // TODO: find a way to get userid
        String price = (String) delegateExecution.getVariable("price");
        // Create user object


        // Send user data to user service
        String response = new RestTemplate().postForObject(USER_SERVICE_URL + userid + "/add/" + price, "", String.class);

        // Handle response (success/failure)
        assert response != null;
        if (response.equals("success")) {
            log.info("Card created successfully!");
        } else {
            log.error("Failed to create card: {}",response);
            throw new BpmnError("Failed to create card");
        }
    }
}
