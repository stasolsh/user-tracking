package com.user.tracking.imperative.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClickReportDto {
    private int totalClicks;
    private int uniqueUsers;
}
