package com.example.cyclingdemo.controller;

import com.example.cyclingdemo.documents.CountryDocument;
import com.example.cyclingdemo.services.CountryService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public Flux<CountryDocument> getAllCountries(){
        return countryService.getAllCountries();
    }

    @GetMapping("/{countryId}")
    public Mono<CountryDocument> getCountryById(@PathVariable String countryId){
        return countryService.getCountryById(countryId);
    }

    @PostMapping("/createcountry")
    public Mono<CountryDocument> createCountry (@RequestBody CountryDocument country){
        return countryService.saveCountry(country);
    }



}
