package com.awin.cod.challenge.imperative.controller;

import com.awin.cod.challenge.imperative.entity.ClickDto;
import com.awin.cod.challenge.imperative.exception.BusinessException;
import com.awin.cod.challenge.imperative.service.ClickService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clicks")
@Tag(name = "Clicks", description = "Clicks management API")
public class ClickController {
    private final ClickService clickService;

    @PostMapping("/record")
    public ResponseEntity<ClickDto> saveClick(@RequestBody ClickDto click) {
        log.info("Entering to ClickController.saveClick() called with payload: {}", click);
        if (click.getPublisherId() == null || click.getUserId() == null) {
            throw new BusinessException("publisherId or userId cannot be null");
        }
        return ResponseEntity.ok(clickService.saveClick(click));
    }
}
