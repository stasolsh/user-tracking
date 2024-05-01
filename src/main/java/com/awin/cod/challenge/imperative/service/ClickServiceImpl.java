package com.awin.cod.challenge.imperative.service;

import com.awin.cod.challenge.imperative.entity.Click;
import com.awin.cod.challenge.imperative.entity.ClickDto;
import com.awin.cod.challenge.imperative.entity.ClickReportDto;
import com.awin.cod.challenge.imperative.mapper.ClickMapper;
import com.awin.cod.challenge.imperative.repository.ClickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClickServiceImpl implements ClickService {
    private final ClickRepository clickRepository;
    private final ClickMapper clickMapper;

    @Override
    public ClickDto saveClick(ClickDto click) {
        return clickMapper.toDto(
                clickRepository.save(clickMapper.toEntity(click)));
    }

    @Override
    public ClickReportDto getReport(Date startDate, Date endDate) {
        List<Click> clicks = clickRepository.getClicksBetweenDates(startDate, endDate);
        int uniqueUsers = clickRepository.findUniqueUsersInRange(startDate, endDate);
        return new ClickReportDto(clicks.size(), uniqueUsers);
    }
}
