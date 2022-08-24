package com.example.cyclingdemo.services;

import com.example.cyclingdemo.documents.CyclingTeamDocument;
import com.example.cyclingdemo.models.Country;
import com.example.cyclingdemo.models.CyclingTeam;
import com.example.cyclingdemo.models.Cyclist;
import com.example.cyclingdemo.repositories.CountryRepository;
import com.example.cyclingdemo.repositories.CyclingTeamRepository;
import com.example.cyclingdemo.repositories.CyclistRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CyclingTeamService {
    private final CyclingTeamRepository cyclingTeamRepository;
    private final CountryRepository countryRepository;
    private final CyclistRepository cyclistRepository;

    public CyclingTeamService(CyclingTeamRepository cyclingTeamRepository, CountryRepository countryRepository, CyclistRepository cyclistRepository) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.countryRepository = countryRepository;
        this.cyclistRepository = cyclistRepository;
    }

    public Mono<CyclingTeamDocument> saveCyclingTeam (CyclingTeamDocument cyclingTeam){
      /** return   countryRepository.findById(cyclingTeam.getCountry().getCountryId()).map(countryDocument -> {
            countryDocument.getCyclingTeams().add(new CyclingTeam(cyclingTeam.getCyclingTeamId(),cyclingTeam.getName(),cyclingTeam.getTeamCode(),cyclingTeam.getCountry(),cyclingTeam.getCyclists()));
            System.out.println(countryDocument.getCyclingTeams());
            return countryDocument;
        }).flatMap(countryRepository::save).flatMap(countryDocument -> {
            cyclingTeam.setCountry(new Country(countryDocument.getCountryId(),countryDocument.getName(),countryDocument.getCode(),countryDocument.getCyclingTeams(),countryDocument.getCyclists()));
            return cyclingTeamRepository.save(cyclingTeam);
       });*/

       return cyclingTeamRepository.save(cyclingTeam).flatMap(cyclingTeamDocument -> {
          return countryRepository.findById(cyclingTeamDocument.getCountry().getCountryId()).map(countryDocument -> {
               countryDocument.getCyclingTeams().add(new CyclingTeam(cyclingTeamDocument.getCyclingTeamId(),cyclingTeamDocument.getName(),cyclingTeamDocument.getTeamCode(),cyclingTeamDocument.getCountry(),cyclingTeamDocument.getCyclists()));
               return countryDocument;
           }).flatMap(countryRepository::save).map(countryDocument -> {
               cyclingTeamDocument.setCountry(new Country(countryDocument.getCountryId(),countryDocument.getName(),countryDocument.getCode(),countryDocument.getCyclingTeams(),countryDocument.getCyclists()));

               return cyclingTeamDocument;

           });

       }).flatMap(cyclingTeamRepository::save);

    }

    public Flux<CyclingTeamDocument> getAllCyclingTeams(){
        return cyclingTeamRepository.findAll();
    }

    public Mono<CyclingTeamDocument> getCyclingTeamById(String cyclingTeamId){
        return cyclingTeamRepository.findById(cyclingTeamId);
    }

    public Mono<Void> deleteCyclingTeam(String cyclingTeamId){
        return cyclingTeamRepository.findById(cyclingTeamId).flatMap(cyclingTeamDocument -> {
            cyclistRepository.findAllByCyclingTeamTeamCode(cyclingTeamDocument.getTeamCode()).flatMap(cyclistRepository::delete);
            return cyclingTeamRepository.deleteById(cyclingTeamDocument.getCyclingTeamId());
        });
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
