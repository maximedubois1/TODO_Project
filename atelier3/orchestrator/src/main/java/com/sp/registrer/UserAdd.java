package com.sp.registrer;

import jakarta.inject.Named;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.*;

@Component
@Named
@RequiredArgsConstructor
@Log4j2
public class UserAdd implements JavaDelegate {
    private final WebClient.Builder webClientBuilder;
    @Value("${endpoint.addresses}")
    protected String addressesEndpoint;
    private final AddressVariableRetrieverForRollBack addressAdapterParamToRollBack;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            AddressDTO savedAddress =
                    addressAdapterParamToRollBack.getAddressDTOtoRollback(delegateExecution);
            webClientBuilder.build().delete()
                    .uri(addressesEndpoint + "/" + savedAddress.getCode())
                    .retrieve()
                    .bodyToMono(Void.class).block();
        } catch (WebClientResponseException ex) {
            log.error("An error has occurred while rolling back address update task : {}", ex.getMessage());
            throw new ProcessException("An error has occurred while rolling back address update task: " +
                    ex.getMessage());
        }
    }
}
