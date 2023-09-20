package com.rho.cli.api.controller;

import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;
import com.rho.cli.api.domain.consultation.ScheduleConsultationService;
import com.rho.cli.api.domain.consultation.ScheduleDetailsConsultationDTO;
import com.rho.cli.api.domain.doctor.Specialization;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
@AutoConfigureJsonTesters
class ConsultationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<ScheduleConsultationDTO> consultationDTOJacksonTester;
    @Autowired
    private JacksonTester<ScheduleDetailsConsultationDTO> detailsConsultationDTOJacksonTester;
    @MockBean
    private ScheduleConsultationService service;

    @Test
    @DisplayName("Must return a 400 status code when the request body is invalid")
    void scheduleConsultationCase1() throws Exception {
        //given //when
        var response = mockMvc.perform(post("/api/consultation")).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Must return a 200 status code when the request body is valid")
    void scheduleConsultationCase2() throws Exception {
        //given
//        var date = LocalDateTime.of(2024,10,10,10,0,0);
        var date = LocalDateTime.now().plusHours(1);
        var specialization = Specialization.CARDIOLOGIST;
        var data = new ScheduleDetailsConsultationDTO(
                null,
                1L,
                1L,
                date
        );
        var expectedJson = detailsConsultationDTOJacksonTester.write(data).getJson();

        // when
        when(service.schelude(any())).thenReturn(data);

        var response = mockMvc.perform(post("/api/consultation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(consultationDTOJacksonTester.write(
                        new ScheduleConsultationDTO(
                                1L,
                                1L,
                                date,
                                specialization
                )).getJson()
                )
        ).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}