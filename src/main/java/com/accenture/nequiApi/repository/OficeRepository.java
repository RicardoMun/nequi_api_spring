package com.accenture.nequiApi.repository;

import com.accenture.nequiApi.model.Ofice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficeRepository extends MongoRepository<Ofice, String> { }
