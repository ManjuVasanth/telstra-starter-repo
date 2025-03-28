package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.ActuationResult;
import au.com.telstra.simcardactivator.model.SimCard;
import au.com.telstra.simcardactivator.model.SimCardRecord;
import au.com.telstra.simcardactivator.repository.SimCardRepository;

public class DatabaseConduit {
    private final SimCardRepository simCardRepository;

    public DatabaseConduit(SimCardRepository simCardRepository) {
        this.simCardRepository = simCardRepository;
    }

    public void save(SimCard simCard, ActuationResult actuationResult) {
        SimCardRecord simCardRecord = new SimCardRecord(simCard, actuationResult);
        simCardRepository.save(simCardRecord);
    }

    public SimCard querySimCard(long simCardId) {
        var simCardRecord = simCardRepository.findById(simCardId).orElse(null);
        if (simCardRecord == null) {
            return null;
        }
        return new SimCard(simCardRecord);
    }
}
