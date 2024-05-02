package com.awin.cod.challenge.imperative.controller;

import com.awin.cod.challenge.imperative.entity.ClickReportDto;
import com.awin.cod.challenge.imperative.service.ClickService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReportController.class)
@AutoConfigureJsonTesters
public class ReportControllerTest {
    private static final String API_REPORTS = "/api/v1/reports/clicks";
    private static final ClickReportDto REPORT_DTO = new ClickReportDto(3, 4);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClickService clickService;
    @Autowired
    private JacksonTester<ClickReportDto> dtoJacksonTester;

    @Test
    public void shouldGetReport() throws Exception {
        when(clickService.getReport(any(Date.class), any(Date.class))).thenReturn(REPORT_DTO);

        ClickReportDto result = dtoJacksonTester.parseObject(mockMvc.perform(
                        MockMvcRequestBuilders.get(API_REPORTS)
                                .param("startDate", "2000-10-29 14:56:59")
                                .param("endDate", "2024-10-29 14:56:59"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
        assertEquals(REPORT_DTO, result);
    }
}
