package com.example.cyclingdemo.cyclist;

import com.example.cyclingdemo.documents.CyclistDocument;
import com.example.cyclingdemo.models.Country;
import com.example.cyclingdemo.models.CyclingTeam;
import com.example.cyclingdemo.repositories.CyclistRepository;
import com.example.cyclingdemo.services.CyclistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCyclistTest {
   @InjectMocks
   private CyclistService cyclistService;
    @Mock
    CyclistRepository cyclistRepository;

    private CyclistDocument cyclistDocument;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCyclist(){
       cyclistDocument= CyclistDocument.builder().cyclistId("A1").competitorNumber(1).fullName("Erick Diaz").country(Country.builder().id("C1").name("Colombia").code("COL").cyclists(new ArrayList<>()).cyclingTeams(new ArrayList<>()).build()).cyclingTeam(CyclingTeam.builder().country(Country.builder().id("C1").name("England").code("ENG").cyclists(new ArrayList<>()).cyclingTeams(new ArrayList<>()).build()).build()).build();
        when(cyclistRepository.save(cyclistDocument)).thenReturn(Mono.just(cyclistDocument));

        StepVerifier.create(cyclistService.saveCyclist(cyclistDocument)).assertNext(cyclistDocument1 -> {
            Assertions.assertEquals(12,cyclistDocument1.getCompetitorNumber());
        }).expectComplete().verify();
    }
}
