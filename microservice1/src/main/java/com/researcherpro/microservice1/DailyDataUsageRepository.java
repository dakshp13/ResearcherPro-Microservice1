package com.researcherpro.microservice1;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DailyDataUsageRepository extends MongoRepository<DailyDataUsage, ObjectId> {
    Optional<DailyDataUsage> findFirstByDate(String date);
}
