package com.awin.cod.challenge.imperative.service;

import com.awin.cod.challenge.imperative.entity.ClickDto;
import com.awin.cod.challenge.imperative.entity.ClickReportDto;

import java.util.Date;

public interface ClickService {
    ClickDto saveClick(ClickDto click);

    ClickReportDto getReport(Date startDate, Date endDate);
}
