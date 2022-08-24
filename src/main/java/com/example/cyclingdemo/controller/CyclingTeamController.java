package com.example.cyclingdemo.controller;

import com.example.cyclingdemo.documents.CyclingTeamDocument;
import com.example.cyclingdemo.models.CyclingTeam;
import com.example.cyclingdemo.services.CountryService;
import com.example.cyclingdemo.services.CyclingTeamService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/api/cyclingteam")
public class CyclingTeamController {
    private final CyclingTeamService cyclingTeamService;

    public CyclingTeamController(CyclingTeamService cyclingTeamService) {
        this.cyclingTeamService = cyclingTeamService;

    }

    @GetMapping
    public Flux<CyclingTeamDocument> getAllCyclingTeams(){
        return cyclingTeamService.getAllCyclingTeams();
    }
    @GetMapping("/{cyclingTeamId}")
    public Mono<CyclingTeamDocument> getCyclingTeamById(@PathVariable String cyclingTeamId){
        return cyclingTeamService.getCyclingTeamById(cyclingTeamId);
    }
    @GetMapping("/teamsofcountry/{countryname}")
    public Flux<CyclingTeamDocument> getAllCyclingTeamsForCountry(@PathVariable String countryname){
        return cyclingTeamService.getTeamByCountry(countryname);
    }

    @PostMapping("/createteam")
    public Mono<CyclingTeamDocument> createCyclingTeam(@RequestBody CyclingTeamDocument cyclingTeamDocument){
        return cyclingTeamService.saveCyclingTeam(cyclingTeamDocument);
    }

    @DeleteMapping("/deleteteam/{cyclingTeamId}")
    public Mono<Void> deleteCyclingTeam(@PathVariable String cyclingTeamId){
        return cyclingTeamService.deleteCyclingTeam(cyclingTeamId);
    }
}
