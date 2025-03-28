package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.model.SimCard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.*;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;
    private SimCard simCard;
    @Given( "a functional sim card")
    public void functionalSimCard(){
        simCard=new SimCard("1255789453849037777","david.jones@gmail.com",true);
    }
    @Given( "a broken sim card")
    public void brokenSimCard(){
        simCard=new SimCard("8944500102198304826","faj.james@gmail.com",false);
    }
    @When("a request to activate the sim card is submitted")
    public void aRequestToActivateTheSimCardIsSubmitted() {
        this.restTemplate.postForObject("http://localhost:8444/actuate", simCard, String.class);
    }

    @Then("the sim card is activated and its state is recorded to the database")
    public void theSimCardIsActivatedAndItsStateIsRecordedToTheDatabase() {
        SimCard simCard = this.restTemplate.getForObject("http://localhost:8080/sim-card/query?simCardId={simCardId}", SimCard.class, 1);
        assertNotNull("Sim card should not be null", simCard);
        assertTrue("Sim card should be active", simCard.isActive());
    }

    @Then("the sim card fails to activate and its state is recorded to the database")
    public void theSimCardFailsToActivateAndItsStateIsRecordedToTheDatabase() {
        SimCard simCard = this.restTemplate.getForObject("http://localhost:8080/sim-card/query?simCardId={simCardId}", SimCard.class, 2);
        assertFalse("Sim card should not be active", simCard.isActive());
    }

}