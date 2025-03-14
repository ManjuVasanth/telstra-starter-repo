package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.SimCard;
import au.com.telstra.simcardactivator.service.SimCardActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimCardActivationRestcontroller {
@Autowired
    private SimCardActivationService simCardActivationService;

    public SimCardActivationRestcontroller(SimCardActivationService simCardActivationService) {
        this.simCardActivationService = simCardActivationService;
    }

    @PostMapping(value="/activate")
    public void handleActivationRequest(@RequestBody SimCard simCard){

   }
}
