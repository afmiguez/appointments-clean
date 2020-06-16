package me.afmiguez.projects.appointmentsservice.usecases.interfaces;

import me.afmiguez.projects.appointmentsdomain.models.Student;

public interface CreateStudentUseCase {
    Student createStudent(Student student);
}
