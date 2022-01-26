package com.example.AirlineManagementSystem.service;


import com.example.AirlineManagementSystem.dto.AirlineNameAndCurrentBalance;
import com.example.AirlineManagementSystem.model.Airline;
import com.example.AirlineManagementSystem.repository.AirlineRepo;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class AirlineService {


    @Autowired
    AirlineRepo airlineRepo;

    @Transactional
    public List<AirlineNameAndCurrentBalance> getAllAirlinesWithCurrentBalance() {
        log.info("invoking retrieveListOfAirlinesAndTheirCurrentBalance Api");
        airlineRepo.findAll();
        List<AirlineNameAndCurrentBalance> airlineNameAndCurrentBalanceList = new ArrayList<>();
        airlineRepo.findAll().forEach(i -> {
            AirlineNameAndCurrentBalance airlineNameAndCurrentBalance = new AirlineNameAndCurrentBalance();
            airlineNameAndCurrentBalance.setName(i.getName());
            airlineNameAndCurrentBalance.setCurrentBalance(i.getCurrentBalance());
            airlineNameAndCurrentBalanceList.add(airlineNameAndCurrentBalance);
        });
        return airlineNameAndCurrentBalanceList;

    }


    @Transactional
    public void saveAirline(Airline airline) {
        log.info("Anew Request Has Been Made To Save Airline With Details : {}, ", airline);
        airlineRepo.save(airline);
    }
}
