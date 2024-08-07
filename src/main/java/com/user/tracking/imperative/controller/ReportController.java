package com.user.tracking.imperative.controller;

import com.user.tracking.imperative.entity.ClickReportDto;
import com.user.tracking.imperative.service.ClickService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Log4j2
@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@Tag(name = "Reports", description = "Reports management API")
public class ReportController {
    private final ClickService clickService;

    @GetMapping("/clicks")
    public ResponseEntity<ClickReportDto> getClickReport(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        log.info("Entering to ReportController.getClickReport() called with startDate: {} and endDate: {}", startDate, endDate);
        return ResponseEntity.ok(clickService.getReport(startDate, endDate));
    }
}
