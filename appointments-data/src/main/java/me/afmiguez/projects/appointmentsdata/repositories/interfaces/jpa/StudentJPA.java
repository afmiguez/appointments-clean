package me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa;

import me.afmiguez.projects.appointmentsdata.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentJPA extends CrudRepository<StudentEntity,Long> {
    Optional<StudentEntity> findByStudentNumber(String number);
}
