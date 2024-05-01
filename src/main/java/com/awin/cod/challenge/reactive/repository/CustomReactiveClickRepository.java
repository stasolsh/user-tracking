package com.awin.cod.challenge.reactive.repository;

import reactor.core.publisher.Flux;

import java.util.Date;

public interface CustomReactiveClickRepository {
    Flux<String> findUniqueUsersInRange(Date start, Date end);
}
