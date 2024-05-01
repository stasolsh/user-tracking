package com.awin.cod.challenge.imperative.repository;

import com.awin.cod.challenge.imperative.entity.Click;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;


@RequiredArgsConstructor
public class CustomClickRepositoryImpl implements CustomClickRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public int findUniqueUsersInRange(Date start, Date end) {
        Query query = new Query();
        query.addCriteria(Criteria.where("timestamp").gte(start).lt(end));
        return mongoTemplate.findDistinct(query, "userId", Click.class, String.class).size();
    }
}
