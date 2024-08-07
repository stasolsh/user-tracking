package com.user.tracking.reactive.repository;

import com.user.tracking.imperative.entity.Click;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;

@Repository
public interface ReactiveClickRepository extends ReactiveMongoRepository<Click, String>, CustomReactiveClickRepository {

    @Query("{ 'timestamp' : { $gt: ?0, $lt: ?1 } }")
    Flux<Click> getClicksBetweenDates(
            Date startGT,
            Date endLT);
}
