package com.awin.cod.challenge.imperative.controller;

import com.awin.cod.challenge.imperative.entity.ClickDto;
import com.awin.cod.challenge.imperative.exception.StandardError;
import com.awin.cod.challenge.imperative.service.ClickService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClickController.class)
@AutoConfigureJsonTesters
public class ClickControllerTest {
    private static final String API_CLICKS = "/api/v1/clicks/record";
    private static final ClickDto CLICK_DTO = new ClickDto("1", "1", "1", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "description");
    private static final ClickDto WRONG_CLICK_DTO = new ClickDto("1", null, "1", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "description");
    private static final String ERROR = "PublisherId and userId cannot be null.";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClickService clickService;

    @Autowired
    private JacksonTester<ClickDto> clickDtoIn;
    @Autowired
    private JacksonTester<ClickDto> clickDtoOut;
    @Autowired
    private JacksonTester<StandardError> errorJacksonTester;

    @Test
    public void shouldSaveAndVerifyRecord() throws Exception {
        when(clickService.saveClick(any(ClickDto.class))).thenReturn(CLICK_DTO);

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post(API_CLICKS)
                                .content(clickDtoIn.write(CLICK_DTO).getJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ClickDto result = clickDtoOut.parseObject(mvcResult.getResponse().getContentAsString());
        assertEquals(CLICK_DTO.toString(), result.toString());
    }

    @Test
    public void shouldReturnServerErrorForInappropriateInput() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post(API_CLICKS)
                                .content(clickDtoIn.write(WRONG_CLICK_DTO).getJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andReturn();
        StandardError result = errorJacksonTester.parseObject(mvcResult.getResponse().getContentAsString());

        assertNotNull(result);
        assertEquals(API_CLICKS, result.getPath());
        assertEquals(ERROR, result.getError());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getStatus());
    }
}
