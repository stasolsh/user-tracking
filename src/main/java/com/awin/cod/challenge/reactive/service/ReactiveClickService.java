package com.awin.cod.challenge.reactive.service;

import com.awin.cod.challenge.imperative.entity.Click;
import com.awin.cod.challenge.imperative.entity.ClickDto;
import com.awin.cod.challenge.imperative.entity.ClickReportDto;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ReactiveClickService {
    Mono<Click> saveClick(ClickDto click);

    Mono<ClickReportDto> getReport(Date startDate, Date endDate);
}
