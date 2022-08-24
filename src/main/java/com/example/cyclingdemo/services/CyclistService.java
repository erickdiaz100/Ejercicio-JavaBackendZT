package com.example.cyclingdemo.services;

import com.example.cyclingdemo.documents.CyclistDocument;
import com.example.cyclingdemo.models.Country;
import com.example.cyclingdemo.models.CyclingTeam;
import com.example.cyclingdemo.models.Cyclist;
import com.example.cyclingdemo.repositories.CountryRepository;
import com.example.cyclingdemo.repositories.CyclingTeamRepository;
import com.example.cyclingdemo.repositories.CyclistRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class CyclistService {
    private final CyclistRepository cyclistRepository;
    private final CountryRepository countryRepository;
    private final CyclingTeamRepository cyclingTeamRepository;
    public CyclistService(CyclistRepository cyclistRepository, CountryRepository countryRepository, CyclingTeamRepository cyclingTeamRepository) {
        this.cyclistRepository = cyclistRepository;
        this.countryRepository = countryRepository;
        this.cyclingTeamRepository = cyclingTeamRepository;
    }

    public Mono<CyclistDocument> saveCyclist (CyclistDocument cyclist){
              return cyclistRepository.save(cyclist).flatMap(cyclistDocument -> {
                  return countryRepository.findById(cyclist.getCountry().getCountryId()).map(countryDocument -> {
                      countryDocument.getCyclists().add(new Cyclist(cyclist.getCyclistId(),cyclist.getFullName(),cyclist.getCompetitorNumber(),cyclist.getCountry(),cyclist.getCyclingTeam()));

                      return countryDocument;
                  }).flatMap(countryRepository::save).map(countryDocument -> {
                      cyclistDocument.setCountry(new Country(countryDocument.getCountryId(),countryDocument.getName(),countryDocument.getCode(),countryDocument.getCyclingTeams(),countryDocument.getCyclists()));
                      cyclingTeamRepository.findById(cyclist.getCyclingTeam().getCyclingTeamId()).map(cyclingTeamDocument -> {
                          cyclingTeamDocument.getCyclists().add(new Cyclist(cyclistDocument.getCyclistId(),cyclistDocument.getFullName(),cyclistDocument.getCompetitorNumber(),cyclistDocument.getCountry(),cyclistDocument.getCyclingTeam()));
                          return cyclingTeamDocument;
                      }).flatMap(cyclingTeamRepository::save).map(cyclingTeamDocument -> {
                          cyclistDocument.setCyclingTeam(new CyclingTeam(cyclingTeamDocument.getCyclingTeamId(),cyclingTeamDocument.getName(),cyclingTeamDocument.getTeamCode(),cyclingTeamDocument.getCountry(),cyclingTeamDocument.getCyclists()));
                          return cyclistDocument;
                      });
                      return cyclistDocument;});
              }).flatMap(cyclistRepository::save);



    }

    public Flux<CyclistDocument> getAllCyclist(){
        return cyclistRepository.findAll();
    }

    public Mono<CyclistDocument> getCyclistById(String cyclistId){
        return cyclistRepository.findById(cyclistId);
    }
    public Mono<Void> deleteCyclist(String cyclistId){
       return cyclistRepository.findById(cyclistId).flatMap(cyclistDocument -> {
            countryRepository.findById(cyclistDocument.getCountry().getCountryId()).map(countryDocument -> {
                countryDocument.getCyclists().remove(cyclistDocument);
                return countryDocument;
            }).flatMap(countryRepository::save);
            cyclingTeamRepository.findById(cyclistDocument.getCyclingTeam().getCyclingTeamId()).map(cyclingTeamDocument -> {
                cyclingTeamDocument.getCyclists().remove(cyclistDocument);
                return cyclingTeamDocument;
            }).flatMap(cyclingTeamRepository::save);
            return cyclistRepository.deleteById(cyclistId).flatMap(Mono::justOrEmpty);
        });

    }
    public Flux<CyclistDocument> getCyclistsByNationality(String countryName){
        return cyclistRepository.findAllByCountryName(countryName);
    }
    public Flux<CyclistDocument> getCyclistsByTeamCode(String teamCode){
        return cyclistRepository.findAllByCyclingTeamTeamCode(teamCode);
    }
}
