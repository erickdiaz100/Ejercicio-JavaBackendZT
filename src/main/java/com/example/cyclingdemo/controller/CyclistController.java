package com.example.cyclingdemo.controller;

import com.example.cyclingdemo.documents.CyclistDocument;
import com.example.cyclingdemo.services.CyclistService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/api/cyclist")
public class CyclistController {
    private final CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }

    @GetMapping
    public Flux<CyclistDocument> getAllCyclist(){
        return  cyclistService.getAllCyclist();
    }

    @GetMapping("/{cyclistId}")
    public Mono<CyclistDocument> getCyclistById(@PathVariable String cyclistId){
        return  cyclistService.getCyclistById(cyclistId);
    }
    @GetMapping("/cyclistsofteam/{teamCode}")
    public Flux<CyclistDocument> getAllCyclistByTeamCode(@PathVariable String teamCode){
        return cyclistService.getCyclistsByTeamCode(teamCode);
    }

    @GetMapping("/cyclistsofcountry/{countryName}")
    public Flux<CyclistDocument> getAllCyclistByCountry(@PathVariable String countryName){
        return cyclistService.getCyclistsByNationality(countryName);
    }

    @PostMapping("/createcyclist")
    public Mono<CyclistDocument> createCyclist(@RequestBody CyclistDocument cyclistDocument){
        return cyclistService.saveCyclist(cyclistDocument);
    }
    @DeleteMapping("/deletecyclist/{cyclistId}")
    public Mono<Void> deleteCyclist(@PathVariable String cyclistId){
        return cyclistService.deleteCyclist(cyclistId);
    }
}
