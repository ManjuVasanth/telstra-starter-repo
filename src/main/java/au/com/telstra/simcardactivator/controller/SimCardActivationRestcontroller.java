package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.ActuationResult;
import au.com.telstra.simcardactivator.model.SimCard;
import au.com.telstra.simcardactivator.service.DatabaseConduit;
import au.com.telstra.simcardactivator.service.SimCardActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sim")
public class SimCardActivationRestcontroller {
@Autowired
    private SimCardActivationService simCardActivationService;
    private DatabaseConduit databaseConduit;

    public SimCardActivationRestcontroller(DatabaseConduit databaseConduit,SimCardActivationService simCardActivationService) {
        this.databaseConduit = databaseConduit;
        this.simCardActivationService = simCardActivationService;
    }

    @PostMapping(value="/activate")
    public void handleActivationRequest(@RequestBody SimCard simCard){
        ActuationResult actuationResult = simCardActivationService.actuate(simCard);
        databaseConduit.save(simCard, actuationResult);
   }
  @GetMapping(value="/query")
   public SimCard handleActivationRequest(@RequestParam Long simCardId){
        return databaseConduit.querySimCard(simCardId);
   }
}

