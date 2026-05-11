package com.user.tracking.reactive.integration;

import com.user.tracking.imperative.entity.Click;
import com.user.tracking.reactive.repository.ReactiveClickRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.text.SimpleDateFormat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class ReactiveReportControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveClickRepository reactiveClickRepository;

    private final SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    public void setup() {
        reactiveClickRepository.deleteAll().block();
    }

    @Test
    public void shouldReturnClickReport() throws Exception {
        reactiveClickRepository.save(createClick("pub-1", "user-1", "2025-01-10 10:00:00")).block();
        reactiveClickRepository.save(createClick("pub-2", "user-2", "2025-01-10 11:00:00")).block();
        reactiveClickRepository.save(createClick("pub-3", "user-1", "2025-01-10 12:00:00")).block();

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/reactive/reports/clicks")
                        .queryParam("startDate", "2025-01-10 09:00:00")
                        .queryParam("endDate", "2025-01-10 13:00:00")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.totalClicks").isEqualTo(3)
                .jsonPath("$.uniqueUsers").isEqualTo(2);
    }

    @Test
    public void shouldReturnEmptyReportWhenNoClicksInDateRange() throws Exception {
        reactiveClickRepository.save(createClick("pub-1", "user-1", "2025-01-10 10:00:00")).block();

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/reactive/reports/clicks")
                        .queryParam("startDate", "2025-01-11 09:00:00")
                        .queryParam("endDate", "2025-01-11 13:00:00")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.totalClicks").isEqualTo(0)
                .jsonPath("$.uniqueUsers").isEqualTo(0);
    }

    private Click createClick(String publisherId, String userId, String timestamp) throws Exception {
        Click click = new Click();
        click.setPublisherId(publisherId);
        click.setUserId(userId);
        click.setTimestamp(formatter.parse(timestamp));
        return click;
    }
}