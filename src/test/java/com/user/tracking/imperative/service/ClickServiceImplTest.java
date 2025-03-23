package com.user.tracking.imperative.service;

import com.user.tracking.imperative.ApplicationTestConfiguration;
import com.user.tracking.imperative.MongoDBTestContainerConfig;
import com.user.tracking.imperative.entity.ClickDto;
import com.user.tracking.imperative.repository.ClickRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@Testcontainers
@EnableAutoConfiguration
@ContextConfiguration(classes = {MongoDBTestContainerConfig.class, ApplicationTestConfiguration.class})
@ActiveProfiles("test")
public class ClickServiceImplTest {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    @Autowired
    private ClickServiceImpl clickService;
    @Autowired
    private ClickRepository clickRepository;

    @Test
    public void saveClick() throws ParseException {
        //when
        clickService.saveClick(new ClickDto("1", "10", "100", FORMAT.parse("2012-07-09 14:58:00.000000"), "description1"));
        clickService.saveClick(new ClickDto("2", "20", "200", FORMAT.parse("2012-07-13 14:58:00.000000"), "description2"));
        clickService.saveClick(new ClickDto("3", "30", "300", FORMAT.parse("2012-07-20 14:58:00.000000"), "description3"));
        //then
        assertEquals(2, clickService.getReport(FORMAT.parse("2012-07-10 14:58:00.000000"), FORMAT.parse("2012-07-21 14:58:00.000000")).getUniqueUsers());
        assertEquals(2, clickService.getReport(FORMAT.parse("2012-07-10 14:58:00.000000"), FORMAT.parse("2012-07-21 14:58:00.000000")).getTotalClicks());
    }

    @AfterEach
    void tearDown() {
        clickRepository.deleteAll();
    }
}