package com.awin.cod.challenge.reactive.controller;

import com.awin.cod.challenge.imperative.entity.Click;
import com.awin.cod.challenge.imperative.entity.ClickDto;
import com.awin.cod.challenge.imperative.exception.BusinessException;
import com.awin.cod.challenge.reactive.service.ReactiveClickService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/api/v1/reactive/clicks")
@RequiredArgsConstructor
@Tag(name = "Clicks reactive", description = "Clicks management API")
public class ReactiveClickController {
    private final ReactiveClickService reactiveClickService;

    @PostMapping("/record")
    public Mono<Click> saveClick(@RequestBody ClickDto click) {
        log.info("Entering to ClickControllerReactive.saveClick() called with payload: {}", click);
        if (click.getPublisherId() == null || click.getUserId() == null) {
            throw new BusinessException("publisherId and userId cannot be null");
        }
        return reactiveClickService.saveClick(click);
    }
}
