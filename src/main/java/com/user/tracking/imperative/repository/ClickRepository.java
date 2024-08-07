package com.user.tracking.imperative.repository;

import com.user.tracking.imperative.entity.Click;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClickRepository extends MongoRepository<Click, Long>, CustomClickRepository {
    @Query("{ 'timestamp' : { $gt: ?0, $lt: ?1 } }")
    List<Click> getClicksBetweenDates(
            Date startDate,
            Date endDate);
}
