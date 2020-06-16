package me.afmiguez.projects.appointmentsservice.usecases.interfaces;

import me.afmiguez.projects.appointmentsdomain.models.Professor;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface ListProfessorUseCases {
    Iterable<Professor> listAll();
    Iterable<Professor> listProfessorByDay(DayOfWeek dayOfWeek);
    Iterable<Professor> listProfessorByStartTime(LocalTime start);
    Iterable<Professor> listProfessorByEndTime(LocalTime end);
}
