package com.awin.cod.challenge.reactive.service;

import com.awin.cod.challenge.imperative.entity.Click;
import com.awin.cod.challenge.imperative.entity.ClickDto;
import com.awin.cod.challenge.imperative.entity.ClickReportDto;
import com.awin.cod.challenge.imperative.mapper.ClickMapper;
import com.awin.cod.challenge.reactive.repository.ReactiveClickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReactiveClickServiceImpl implements ReactiveClickService {
    private final ReactiveClickRepository reactiveClickRepository;
    private final ClickMapper clickMapper;

    @Override
    public Mono<Click> saveClick(ClickDto click) {
        return reactiveClickRepository.save(clickMapper.toEntity(click)).log();
    }

    @Override
    public Mono<ClickReportDto> getReport(Date startDate, Date endDate) {
        Flux<Click> clicks = reactiveClickRepository.getClicksBetweenDates(startDate, endDate);
        Flux<String> users = reactiveClickRepository.findUniqueUsersInRange(startDate, endDate);
        return clicks.collectList().zipWhen(it ->
                users.collectList(), (click, user) -> new ClickReportDto(click.size(), user.size()));
    }
}
