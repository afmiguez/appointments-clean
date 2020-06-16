package me.afmiguez.projects.appointmentsdata.repositories.interfaces;

import me.afmiguez.projects.appointmentsdomain.models.Student;

import java.util.Optional;

public interface StudentDAO extends BaseDAO<Student,Long> {
    Optional<Student> findByNumber(String number);
}
