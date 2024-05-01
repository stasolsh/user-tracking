package com.awin.cod.challenge.imperative.repository;

import java.util.Date;

public interface CustomClickRepository {
    int findUniqueUsersInRange(Date start, Date end);
}
