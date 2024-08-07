package com.user.tracking.imperative.repository;

import java.util.Date;

public interface CustomClickRepository {
    int findUniqueUsersInRange(Date start, Date end);
}
