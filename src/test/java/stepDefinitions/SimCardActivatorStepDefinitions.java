package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import io.cucumber.java.en.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {

    private final String BASE_URL = "http://localhost:9090/sim";
    private final RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<Map> response;

    @Given("I submit a SIM activation request with ICCID {string} and email {string}")
    public void i_submit_a_SIM_activation_request(String iccid, String email) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("iccid", iccid);
        requestBody.put("customerEmail", email);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        restTemplate.postForEntity(BASE_URL + "/activate", requestEntity, String.class);
    }

    @When("I query the SIM activation record with ID {int}")
    public void i_query_the_sim_activation_record(int id) {
        response = restTemplate.getForEntity(BASE_URL + "/query?simCardId=" + id, Map.class);
    }

    @Then("the response should indicate activation was {string}")
    public void the_response_should_indicate_activation_was(String expectedActivationStatus) {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Boolean.parseBoolean(expectedActivationStatus), response.getBody().get("active"));
    }
}
