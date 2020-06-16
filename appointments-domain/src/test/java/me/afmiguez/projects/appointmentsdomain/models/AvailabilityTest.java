package me.afmiguez.projects.appointmentsdomain.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityTest {

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
    void overlaps() {
        Availability availability= Availability.builder()
                .dayOfWeek(LocalDate.now().getDayOfWeek())
                .start(LocalTime.of(10,0))
                .end(LocalTime.of(12,0))
                .professor(professor)
                .build();

        Appointment appointmentInside= Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(11,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(11,30)))
                .student(student)
                .professor(professor)
                .build();

        assertTrue(availability.canSchedule(appointmentInside));


        Appointment appointmentWholePeriod= Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(12,0)))
                .student(student)
                .professor(professor)
                .build();

        assertTrue(availability.canSchedule(appointmentWholePeriod));

    }

    @Test
    void notOverlaps(){
        Availability availability= Availability.builder()
                .dayOfWeek(LocalDate.now().getDayOfWeek())
                .start(LocalTime.of(10,0))
                .end(LocalTime.of(12,0))
                .professor(professor)
                .build();

        Appointment appointmentBeforeAvailability= Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(9,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(9,30)))
                .student(student)
                .professor(professor)
                .build();

        assertFalse(availability.canSchedule(appointmentBeforeAvailability));


        Appointment appointmentAfterAvailability= Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(12,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(12,30)))
                .student(student)
                .professor(professor)
                .build();

        assertFalse(availability.canSchedule(appointmentAfterAvailability));

    }
}