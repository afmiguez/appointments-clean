package me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa;


import me.afmiguez.projects.appointmentsdomain.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentJPA extends CrudRepository<Student,Long> {
    Optional<Student> findByStudentNumber(String number);
}
