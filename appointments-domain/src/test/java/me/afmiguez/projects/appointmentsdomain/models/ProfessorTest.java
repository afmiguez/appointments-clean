package me.afmiguez.projects.appointmentsdomain.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    private Professor professor=Professor.builder()
            .firstName("firstname")
            .lastName("lastname")
            .email("email")
            .availabilities(new ArrayList<>())
            .appointments(new ArrayList<>())
            .build();

    private Student student=Student.builder()
            .firstName("firstname")
            .lastName("lastname")
            .studentNumber("12345")
            .appointments(new ArrayList<>())
            .build();

    @BeforeEach
    public void resetProfessorCollections(){
        professor.setAppointments(new ArrayList<>());
        professor.setAvailabilities(new ArrayList<>());
    }

    @Test
    void addAvailability() {

        assertEquals(0, professor.getAvailabilities().size());

        professor.addAvailability(Availability.builder()
                .dayOfWeek(LocalDate.now().getDayOfWeek())
                .start(LocalTime.of(10,0))
                .end(LocalTime.of(10,30))
                .professor(professor)
                .build());

        assertEquals(1, professor.getAvailabilities().size());

        professor.addAvailability(Availability.builder()
                .dayOfWeek(LocalDate.now().getDayOfWeek())
                .start(LocalTime.of(10,0))
                .end(LocalTime.of(10,30))
                .professor(professor)
                .build());

        assertEquals(1, professor.getAvailabilities().size());
    }

    @Test
    void addAppointmentOk() {
        assertEquals(0, professor.getAvailabilities().size());

        assertEquals(0, professor.getAvailabilities().size());

        professor.addAvailability(Availability.builder()
                .dayOfWeek(LocalDate.now().getDayOfWeek())
                .start(LocalTime.of(10,0))
                .end(LocalTime.of(12,0))
                .professor(professor)
                .build());

        Appointment atBeginAvailability=Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,30)))
                .student(student)
                .professor(professor)
                .build();

        professor.addAppointment(atBeginAvailability);

        assertEquals(1,professor.getAppointments().size());

        Appointment atEndAvailability=Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(11,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(12,0)))
                .student(student)
                .professor(professor)
                .build();

        professor.addAppointment(atEndAvailability);

        assertEquals(2,professor.getAppointments().size());
    }

    @Test
    void addAppointmentFail() {

        professor.addAvailability(Availability.builder()
                .dayOfWeek(LocalDate.now().getDayOfWeek())
                .start(LocalTime.of(10,0))
                .end(LocalTime.of(12,0))
                .professor(professor)
                .build());

        Appointment beforeBeginAvailability=Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(9,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,0)))
                .student(student)
                .professor(professor)
                .build();

        assertFalse(professor.canAddAppointment(beforeBeginAvailability));

        assertEquals(0,professor.getAppointments().size());

        Appointment beforeBeginAvailabilityEndsInside=Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(9,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,30)))
                .student(student)
                .professor(professor)
                .build();

//        professor.addAppointment(beforeBeginAvailabilityEndsInside);

        assertFalse(professor.canAddAppointment(beforeBeginAvailabilityEndsInside));


        Appointment endAfterAvailability=Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(12,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(12,30)))
                .student(student)
                .professor(professor)
                .build();

//        professor.addAppointment(endAfterAvailability);
        assertFalse(professor.canAddAppointment(endAfterAvailability));

//        assertEquals(0,professor.getAppointments().size());

        Appointment startDuringEndAfterAvailability=Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,30)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(12,30)))
                .student(student)
                .professor(professor)
                .build();

//        professor.addAppointment(startDuringEndAfterAvailability);

//        assertEquals(0,professor.getAppointments().size());

        assertFalse(professor.canAddAppointment(startDuringEndAfterAvailability));

        Appointment validAppointment=Appointment.builder()
                .startTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,0)))
                .endTime(LocalDateTime.of(LocalDate.now(),LocalTime.of(10,30)))
                .student(student)
                .professor(professor)
                .build();

        assertTrue(professor.canAddAppointment(validAppointment));
        professor.addAppointment(validAppointment);

        assertEquals(1,professor.getAppointments().size(),"Should add new Appointment");


        assertFalse(professor.canAddAppointment(validAppointment));

//        assertEquals(1,professor.getAppointments().size(),"Should not add new Appointment. Same period Appointment already scheduled");


    }



}