package com.example.AirlineManagementSystem.service;


import com.example.AirlineManagementSystem.dto.AirlineNameAndCurrentBalance;
import com.example.AirlineManagementSystem.model.Airline;
import com.example.AirlineManagementSystem.repository.AirlineRepo;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Log4j2
public class AirlineService {


    @Autowired
    AirlineRepo airlineRepo;

    public List<AirlineNameAndCurrentBalance> getAllAirlinesWithCurrentBalance(){
       log.info("Anew Request Has Been Made To FindAll Airlines");
      return airlineRepo.finAllSortByCurrentBalanceDesc();

   }
   @SneakyThrows
    public void saveAirline(Airline airline) {
        log.info("Anew Request Has Been Made To Save Airline With Details : {}, ", airline);
       airlineRepo.save(airline);
    }
}
