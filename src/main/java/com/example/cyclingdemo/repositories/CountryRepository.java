package com.example.cyclingdemo.repositories;

import com.example.cyclingdemo.documents.CountryDocument;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends ReactiveMongoRepository<CountryDocument,String> {

}
