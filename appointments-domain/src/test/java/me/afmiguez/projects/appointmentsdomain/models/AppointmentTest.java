package me.afmiguez.projects.appointmentsdomain.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private Student student=Student.builder()
            .firstName("firstname")
            .lastName("lastname")
            .studentNumber("12345")
            .appointments(new ArrayList<>())
            .build();

    private Professor professor=Professor.builder()
            .firstName("firstname")
            .lastName("lastname")
            .email("email")
            .appointments(new ArrayList<>())
            .availabilities(new ArrayList<>())
            .build();

    @Test
    void appointmentsOverlaps() {
        Appointment appointment1= Appointment.builder()
                .professor(professor)
                .student(student)
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,30)))
                .build();


        Appointment appointment2= Appointment.builder()
                .professor(professor)
                .student(student)
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,30)))
                .build();

        assertTrue(appointment1.overlaps(appointment2));
        assertTrue(appointment2.overlaps(appointment1));


        Appointment appointment3= Appointment.builder()
                .professor(professor)
                .student(student)
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(9,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(11,30)))
                .build();

        assertTrue(appointment1.overlaps(appointment3));
        assertTrue(appointment3.overlaps(appointment1));

    }

    @Test
    void appointmentsNotOverlaps() {
        Appointment appointment1= Appointment.builder()
                .professor(professor)
                .student(student)
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,30)))
                .build();

        Appointment appointment2= Appointment.builder()
                .professor(professor)
                .student(student)
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(9,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,0)))
                .build();

        assertFalse(appointment1.overlaps(appointment2));

        Appointment appointment3= Appointment.builder()
                .professor(professor)
                .student(student)
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(9,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(9,30)))
                .build();

        assertFalse(appointment1.overlaps(appointment3));

        Appointment appointment4= Appointment.builder()
                .professor(professor)
                .student(student)
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,30)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(11,30)))
                .build();

        assertFalse(appointment1.overlaps(appointment4));

        Appointment appointment5= Appointment.builder()
                .professor(professor)
                .student(student)
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(11,30)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(12,30)))
                .build();

        assertFalse(appointment1.overlaps(appointment5));


    }

}