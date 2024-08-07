package com.user.tracking.reactive.service;

import com.user.tracking.imperative.entity.Click;
import com.user.tracking.imperative.entity.ClickDto;
import com.user.tracking.imperative.entity.ClickReportDto;
import com.user.tracking.imperative.mapper.ClickMapper;
import com.user.tracking.reactive.repository.ReactiveClickRepository;
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
