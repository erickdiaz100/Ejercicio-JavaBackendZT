package com.example.cyclingdemo.repositories;

import com.example.cyclingdemo.documents.CyclingTeamDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CyclingTeamRepository extends ReactiveMongoRepository<CyclingTeamDocument,String > {

    Flux<CyclingTeamDocument> findAllByCountryName(String nameCountry);
}
