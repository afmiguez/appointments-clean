package me.afmiguez.projects.appointmentsweb.controllers;

import me.afmiguez.projects.appointmentsdomain.models.Appointment;
import me.afmiguez.projects.appointmentsdomain.models.Availability;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import me.afmiguez.projects.appointmentsdomain.models.Student;
import me.afmiguez.projects.appointmentsweb.dtos.AppointmentCreateDTO;
import me.afmiguez.projects.appointmentsweb.dtos.AvailabilityCreateDTO;
import me.afmiguez.projects.appointmentsweb.dtos.ProfessorCreateDTO;
import me.afmiguez.projects.appointmentsweb.dtos.StudentCreateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppointmentControllerTest {

    @LocalServerPort
    private int port;

    private String domain="http://localhost:";

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void completeTest(){
        Professor professor= Professor.builder()
                .firstName("firstname")
                .lastName("lastname")
                .email("abc@def.org")
                .appointments(new ArrayList<>())
                .availabilities(new ArrayList<>())
                .build();

        ResponseEntity<ProfessorCreateDTO> professorResponseEntity=restTemplate.postForEntity(createURLWithPort("/professor"),professor, ProfessorCreateDTO.class);
        assertTrue(professorResponseEntity.hasBody());
        assertTrue(professorResponseEntity.getStatusCode().is2xxSuccessful());

        Student student= Student.builder()
                .firstName("firstname")
                .lastName("lastname")
                .studentNumber("12345")
                .appointments(new ArrayList<>())
                .build();

        ResponseEntity<StudentCreateDTO> studentResponseEntity=restTemplate.postForEntity(createURLWithPort("/student"),student, StudentCreateDTO.class);
        assertTrue(studentResponseEntity.hasBody());
        assertTrue(studentResponseEntity.getStatusCode().is2xxSuccessful());

        Availability availability= Availability.builder()
                .dayOfWeek(DayOfWeek.THURSDAY)
                .start(LocalTime.of(8,0))
                .end(LocalTime.of(12,0))
                .professor(professor)
                .build();

        ResponseEntity<AvailabilityCreateDTO> availabilityResponseEntity=restTemplate.postForEntity(createURLWithPort("/availability"),availability, AvailabilityCreateDTO.class);
        assertTrue(availabilityResponseEntity.hasBody());
        assertTrue(availabilityResponseEntity.getStatusCode().is2xxSuccessful());


        Appointment appointment= Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.of(1970,1,1),LocalTime.of(8,0)))
                .endTime(LocalDateTime.of(LocalDate.of(1970,1,1),LocalTime.of(8,30)))
                .professor(professor)
                .student(student)
                .build();

        ResponseEntity<AppointmentCreateDTO> appointmentResponseEntity=restTemplate.postForEntity(createURLWithPort("/appointment"),appointment,AppointmentCreateDTO.class);
        assertTrue(appointmentResponseEntity.hasBody());
        assertTrue(appointmentResponseEntity.getStatusCode().is2xxSuccessful());


        appointmentResponseEntity=restTemplate.postForEntity(createURLWithPort("/appointment"),appointment,AppointmentCreateDTO.class);
        assertFalse(appointmentResponseEntity.hasBody());
        assertTrue(appointmentResponseEntity.getStatusCode().is4xxClientError());

    }



    private String createURLWithPort(String uri) {
        return domain + port + uri;
    }

}