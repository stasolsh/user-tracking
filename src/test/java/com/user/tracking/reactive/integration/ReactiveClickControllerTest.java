package com.user.tracking.reactive.integration;

import com.user.tracking.imperative.entity.Click;
import com.user.tracking.imperative.entity.ClickDto;
import com.user.tracking.reactive.repository.ReactiveClickRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class ReactiveClickControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveClickRepository reactiveClickRepository;

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

        webTestClient.post()
                .uri("/api/v1/reactive/clicks/record")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Click.class)
                .value(saved -> {
                    assertNotNull(saved.getId());
                    assertEquals("publisher-1", saved.getPublisherId());
                    assertEquals("user-1", saved.getUserId());
                });

        long count = reactiveClickRepository.count().block();

        assertEquals(1, count);
    }

    @Test
    void shouldReturnPreconditionFailedWhenUserIdIsNull() {
        ClickDto dto = new ClickDto();
        dto.setPublisherId("publisher-1");

        webTestClient.post()
                .uri("/api/v1/reactive/clicks/record")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isEqualTo(412)
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("publisherId and userId cannot be null");
    }

    @Test
    void shouldReturnPreconditionFailedWhenPublisherIdIsNull() {
        ClickDto dto = new ClickDto();
        dto.setUserId("user-1");

        webTestClient.post()
                .uri("/api/v1/reactive/clicks/record")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isEqualTo(412)
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("publisherId and userId cannot be null");
    }
}