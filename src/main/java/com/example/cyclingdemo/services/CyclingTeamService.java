package com.example.cyclingdemo.services;

import com.example.cyclingdemo.documents.CountryDocument;
import com.example.cyclingdemo.documents.CyclingTeamDocument;
import com.example.cyclingdemo.models.CyclingTeam;
import com.example.cyclingdemo.models.Cyclist;
import com.example.cyclingdemo.repositories.CountryRepository;
import com.example.cyclingdemo.repositories.CyclingTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CyclingTeamService {
    private final CyclingTeamRepository cyclingTeamRepository;

    public CyclingTeamService(CyclingTeamRepository cyclingTeamRepository) {
        this.cyclingTeamRepository = cyclingTeamRepository;
    }

    public Mono<CyclingTeamDocument> saveCyclingTeam (CyclingTeamDocument cyclingTeam){
        return cyclingTeamRepository.save(cyclingTeam);
    }

    public Flux<CyclingTeamDocument> getAllCyclingTeams(){
        return cyclingTeamRepository.findAll();
    }

    public Mono<CyclingTeamDocument> getCyclingTeamById(String cyclingTeamId){
        return cyclingTeamRepository.findById(cyclingTeamId);
    }

    public Mono<Void> deleteCyclingTeam(String cyclingTeamId){
        return cyclingTeamRepository.deleteById(cyclingTeamId).flatMap(Mono::justOrEmpty);
    }
    public Mono<CyclingTeamDocument> addCyclistToTeam(String cyclingTeamId, Cyclist cyclist){
        return cyclingTeamRepository.findById(cyclingTeamId).map(cyclingTeamDocument -> {
            cyclingTeamDocument.getCyclists().add(cyclist);
            return cyclingTeamDocument;
        }).flatMap(cyclingTeamDocument -> cyclingTeamRepository.save(cyclingTeamDocument));
    }

    public Mono<CyclingTeamDocument> addCyclistsToTeam(String cyclingTeamId, List<Cyclist> cyclists){
        return cyclingTeamRepository.findById(cyclingTeamId).map(cyclingTeamDocument-> {
            cyclingTeamDocument.getCyclists().addAll(cyclists);
            return cyclingTeamDocument;
        }).flatMap(cyclingTeamDocument -> cyclingTeamRepository.save(cyclingTeamDocument));
    }

    public Flux<CyclingTeamDocument> getTeamByCountry(String nameCountry){
       return cyclingTeamRepository.findAllByCountryName(nameCountry);
               //cyclingTeamRepository.findAll().filter(cyclingTeamDocument -> cyclingTeamDocument.getCountry().getName().equals(nameCountry));
    }
}
