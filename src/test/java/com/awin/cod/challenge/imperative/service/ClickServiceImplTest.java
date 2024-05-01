package com.awin.cod.challenge.imperative.service;

import com.awin.cod.challenge.imperative.entity.Click;
import com.awin.cod.challenge.imperative.entity.ClickDto;
import com.awin.cod.challenge.imperative.entity.ClickReportDto;
import com.awin.cod.challenge.imperative.mapper.ClickMapper;
import com.awin.cod.challenge.imperative.repository.ClickRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ClickServiceImplTest {
    private static final Click CLICK_1 = new Click("6632464a3fa0c8762ea9eb7b", "1", "1", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "description");
    private static final Click CLICK_2 = new Click("5672464a3fa0c8762ea9eb7b", "2", "2", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "description");
    private static final ClickDto CLICK_DTO = new ClickDto("1", "1", "1", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "description");
    private static final int EXPECTED_TOTAL_CLICKS = 2;
    private static final int EXPECTED_UNIQUE_USERS = 2;

    @InjectMocks
    private ClickServiceImpl clickServiceImpl;
    @Mock
    private ClickRepository clickRepository;
    @Mock
    private ClickMapper clickMapper;

    @Test
    public void shouldSaveClick() {
        when(clickRepository.save(any(Click.class))).thenReturn(CLICK_1);
        when(clickMapper.toEntity(any(ClickDto.class))).thenReturn(CLICK_1);
        when(clickMapper.toDto(any(Click.class))).thenReturn(CLICK_DTO);

        ClickDto result = clickServiceImpl.saveClick(CLICK_DTO);

        assertEquals(result.getPublisherId(), CLICK_1.getPublisherId());
        assertEquals(result.getUserId(), CLICK_1.getUserId());
        assertEquals(result.getDescription(), CLICK_1.getDescription());
        assertEquals(result.getTimestamp(), CLICK_1.getTimestamp());
        verify(clickRepository).save(any(Click.class));
        verify(clickMapper).toEntity(any(ClickDto.class));
        verify(clickMapper).toDto(any(Click.class));
    }

    @Test
    public void shouldGetReport() {
        when(clickRepository.getClicksBetweenDates(any(Date.class), any(Date.class))).thenReturn(List.of(CLICK_1, CLICK_2));
        when(clickRepository.findUniqueUsersInRange(any(Date.class), any(Date.class))).thenReturn(EXPECTED_TOTAL_CLICKS);

        ClickReportDto result = clickServiceImpl.getReport(new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime(),
                new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());

        assertEquals(EXPECTED_TOTAL_CLICKS, result.getTotalClicks());
        assertEquals(EXPECTED_UNIQUE_USERS, result.getUniqueUsers());
    }
}
