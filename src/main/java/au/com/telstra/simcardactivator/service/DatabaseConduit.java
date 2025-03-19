package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.ActuationResult;
import au.com.telstra.simcardactivator.model.SimCard;
import au.com.telstra.simcardactivator.model.SimCardRecord;
import au.com.telstra.simcardactivator.repository.SimCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
        SimCardRecord simCardRecord = simCardRepository.findById(simCardId).orElse(null);
        return simCardRecord != null ? new SimCard(simCardRecord) : null;
    }
}
