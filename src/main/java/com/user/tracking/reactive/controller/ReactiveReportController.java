package com.user.tracking.reactive.controller;

import com.user.tracking.imperative.entity.ClickReportDto;
import com.user.tracking.reactive.service.ReactiveClickService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@Log4j2
@RestController
@RequestMapping("/api/v1/reactive/reports")
@RequiredArgsConstructor
@Tag(name = "Reports reactive", description = "Reports management API")
public class ReactiveReportController {
    private final ReactiveClickService reactiveClickService;

    @GetMapping(value = "/clicks")
    public Mono<ClickReportDto> getClickReport(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        log.info("Entering to ReportControllerReactive.getClickReport() called with startDate: {} and endDate: {}", startDate, endDate);
        return reactiveClickService.getReport(startDate, endDate);
    }
}
