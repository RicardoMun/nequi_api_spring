package com.accenture.nequiApi.repository;

import com.accenture.nequiApi.model.Franchise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends MongoRepository<Franchise, String> { }


