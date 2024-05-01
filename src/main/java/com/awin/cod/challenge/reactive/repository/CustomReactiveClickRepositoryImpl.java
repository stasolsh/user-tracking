package com.awin.cod.challenge.reactive.repository;

import com.awin.cod.challenge.imperative.entity.Click;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

import java.util.Date;

@RequiredArgsConstructor
public class CustomReactiveClickRepositoryImpl implements CustomReactiveClickRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    @Override
    public Flux<String> findUniqueUsersInRange(Date start, Date end) {
        Query query = new Query();
        query.addCriteria(Criteria.where("timestamp").gte(start).lt(end));
        return reactiveMongoTemplate.findDistinct(query, "userId", Click.class, String.class);
    }
}
