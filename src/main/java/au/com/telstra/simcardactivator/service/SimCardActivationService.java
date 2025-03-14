package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.ActuationResult;
import au.com.telstra.simcardactivator.model.SimCard;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimCardActivationService {
    private final RestTemplate restTemplate;
    private final String incentiveUrl;

    public SimCardActivationService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        this.incentiveUrl= "http://localhost:8444/actuate";
    }
public ActuationResult actuate(SimCard simCard){
        return restTemplate.postForObject(incentiveUrl,simCard,ActuationResult.class);
}
}
