package com.example.cyclingdemo.repositories;

import com.example.cyclingdemo.documents.CyclistDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CyclistRepository extends ReactiveMongoRepository<CyclistDocument,String> {
   Flux<CyclistDocument> findAllByCyclingTeamTeamCode(String teamCode);
   Flux<CyclistDocument> findAllByCountryName(String countryName);
}