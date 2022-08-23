package com.example.cyclingdemo.services;

import com.example.cyclingdemo.documents.CountryDocument;
import com.example.cyclingdemo.documents.CyclingTeamDocument;
import com.example.cyclingdemo.models.CyclingTeam;
import com.example.cyclingdemo.models.Cyclist;
import com.example.cyclingdemo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Mono<CountryDocument> saveCountry (CountryDocument country){
        return countryRepository.save(country);
    }

    public Flux<CountryDocument> getAllCountries(){
        return countryRepository.findAll();
    }

    public Mono<CountryDocument> getCountryById(String countryId){
        return countryRepository.findById(countryId);
    }

    public Mono<Void> deleteCountry(String countryId){
        return countryRepository.deleteById(countryId).flatMap(Mono::justOrEmpty);
    }
    public Mono<CountryDocument> addCyclingTeam(String countryId, CyclingTeam cyclingTeam){
        return countryRepository.findById(countryId).map(countryDocument -> {
            countryDocument.getCyclingTeams().add(cyclingTeam);
            return countryDocument;
        }).flatMap(countryDocument -> countryRepository.save(countryDocument));
    }
    public Mono<CountryDocument> addCyclingTeams(String countryId, List<CyclingTeam> cyclingTeams){
        return countryRepository.findById(countryId).map(countryDocument -> {
            countryDocument.getCyclingTeams().addAll(cyclingTeams);
            return countryDocument;
        }).flatMap(countryDocument -> countryRepository.save(countryDocument));
    }
    public Mono<CountryDocument> addCyclist(String countryId, Cyclist cyclist){
        return countryRepository.findById(countryId).map(countryDocument -> {
            countryDocument.getCyclists().add(cyclist);
            return countryDocument;
        }).flatMap(countryDocument -> countryRepository.save(countryDocument));
    }
    public Mono<CountryDocument> addCyclists(String countryId, List<Cyclist> cyclists){
        return countryRepository.findById(countryId).map(countryDocument -> {
            countryDocument.getCyclists().addAll(cyclists);
            return countryDocument;
        }).flatMap(countryDocument -> countryRepository.save(countryDocument));
    }
}
