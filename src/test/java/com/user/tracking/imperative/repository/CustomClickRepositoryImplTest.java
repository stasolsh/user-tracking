package com.user.tracking.imperative.repository;

import com.user.tracking.imperative.ApplicationTestConfiguration;
import com.user.tracking.imperative.MongoDBTestContainerConfig;
import com.user.tracking.imperative.entity.Click;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
public class CustomClickRepositoryImplTest {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    @Autowired
    private CustomClickRepositoryImpl customClickRepository;

    @Autowired
    private ClickRepository clickRepository;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        clickRepository.save(new Click("1", "10", "100", FORMAT.parse("2012-07-10 14:58:00.000000"), "description1"));
        clickRepository.save(new Click("2", "20", "200", FORMAT.parse("2012-07-13 14:58:00.000000"), "description2"));
        clickRepository.save(new Click("3", "30", "300", FORMAT.parse("2012-07-17 14:58:00.000000"), "description3"));
        clickRepository.save(new Click("4", "40", "400", FORMAT.parse("2012-07-20 14:58:00.000000"), "description4"));
        clickRepository.save(new Click("5", "50", "500", FORMAT.parse("2012-07-23 14:58:00.000000"), "description5"));
    }

    @Test
    public void verifyFiltrationResultsInTheGivenRange() throws ParseException {
        //when
        int result = customClickRepository.findUniqueUsersInRange(FORMAT.parse("2012-07-10 14:58:00.000000"), FORMAT.parse("2012-07-21 14:58:00.000000"));
        //then
        assertEquals(4, result);
    }

    @Test
    public void verifyFiltrationResultsOutsideTheRange() throws ParseException {
        //when
        int result = customClickRepository.findUniqueUsersInRange(FORMAT.parse("2017-07-10 14:58:00.000000"), FORMAT.parse("2018-07-21 14:58:00.000000"));
        //then
        assertEquals(0, result);
    }

    @AfterEach
    void tearDown() {
        clickRepository.deleteAll();
    }
}