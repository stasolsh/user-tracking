package com.user.tracking.imperative.service;

import com.user.tracking.imperative.entity.ClickDto;
import com.user.tracking.imperative.entity.ClickReportDto;

import java.util.Date;

public interface ClickService {
    ClickDto saveClick(ClickDto click);

    ClickReportDto getReport(Date startDate, Date endDate);
}
