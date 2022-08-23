package com.example.cyclingdemo.services;

import com.example.cyclingdemo.documents.CyclistDocument;
import com.example.cyclingdemo.repositories.CyclistRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class CyclistService {
    private final CyclistRepository cyclistRepository;

    public CyclistService(CyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }

    public Mono<CyclistDocument> saveCyclist (CyclistDocument cyclist){
        return cyclistRepository.save(cyclist);
    }

    public Flux<CyclistDocument> getAllCyclist(){
        return cyclistRepository.findAll();
    }

    public Mono<CyclistDocument> getCyclistById(String cyclistId){
        return cyclistRepository.findById(cyclistId);
    }
    public Mono<Void> deleteCyclist(String cyclistId){
        return cyclistRepository.deleteById(cyclistId).flatMap(Mono::justOrEmpty);
    }
    public Flux<CyclistDocument> getCyclistsByNationality(String countryName){
        return cyclistRepository.findAllByCountryName(countryName);
    }
    public Flux<CyclistDocument> getCyclistsByTeamCode(String teamCode){
        return cyclistRepository.findAllByCyclingTeamTeamCode(teamCode);
    }
}
