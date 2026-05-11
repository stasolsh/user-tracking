package com.user.tracking.reactive.integration;

import com.user.tracking.imperative.entity.Click;
import com.user.tracking.imperative.entity.ClickDto;
import com.user.tracking.imperative.entity.ClickReportDto;
import com.user.tracking.reactive.repository.ReactiveClickRepository;
import com.user.tracking.reactive.service.ReactiveClickService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ReactiveClickServiceTest {

    @Autowired
    private ReactiveClickService reactiveClickService;

    @Autowired
    private ReactiveClickRepository reactiveClickRepository;

    private final SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    public void setup() {
        reactiveClickRepository.deleteAll().block();
    }

    @Test
    public void shouldSaveClick() {
        ClickDto dto = new ClickDto();
        dto.setPublisherId("publisher-1");
        dto.setUserId("user-1");
        dto.setTimestamp(new Date());

        Click saved = reactiveClickService.saveClick(dto).block();

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("publisher-1", saved.getPublisherId());
        assertEquals("user-1", saved.getUserId());

        Long count = reactiveClickRepository.count().block();
        assertEquals(1L, count);
    }

    @Test
    public void shouldGenerateReport() throws Exception {
        reactiveClickRepository.save(createClick("pub-1", "user-1", "2025-01-10 10:00:00")).block();
        reactiveClickRepository.save(createClick("pub-2", "user-2", "2025-01-10 11:00:00")).block();
        reactiveClickRepository.save(createClick("pub-3", "user-1", "2025-01-10 12:00:00")).block();

        ClickReportDto report = reactiveClickService
                .getReport(
                        formatter.parse("2025-01-10 09:00:00"),
                        formatter.parse("2025-01-10 13:00:00")
                )
                .block();

        assertNotNull(report);
        assertEquals(3, report.getTotalClicks());
        assertEquals(2, report.getUniqueUsers());
    }

    @Test
    public void shouldGenerateEmptyReportWhenNoClicksInRange() throws Exception {
        reactiveClickRepository.save(createClick("pub-1", "user-1", "2025-01-10 10:00:00")).block();

        ClickReportDto report = reactiveClickService
                .getReport(
                        formatter.parse("2025-01-11 09:00:00"),
                        formatter.parse("2025-01-11 13:00:00")
                )
                .block();

        assertNotNull(report);
        assertEquals(0, report.getTotalClicks());
        assertEquals(0, report.getUniqueUsers());
    }

    private Click createClick(String publisherId, String userId, String timestamp) throws Exception {
        Click click = new Click();
        click.setPublisherId(publisherId);
        click.setUserId(userId);
        click.setTimestamp(formatter.parse(timestamp));
        return click;
    }
}