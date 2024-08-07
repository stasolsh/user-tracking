package com.user.tracking.reactive.service;

import com.user.tracking.imperative.entity.Click;
import com.user.tracking.imperative.entity.ClickDto;
import com.user.tracking.imperative.entity.ClickReportDto;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ReactiveClickService {
    Mono<Click> saveClick(ClickDto click);

    Mono<ClickReportDto> getReport(Date startDate, Date endDate);
}
