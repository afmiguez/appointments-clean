package me.afmiguez.projects.appointmentsdata.repositories.interfaces;

import me.afmiguez.projects.appointmentsdata.entities.StudentEntity;

import java.util.Optional;

public interface StudentDAO extends BaseDAO<StudentEntity,Long> {
    Optional<StudentEntity> findByNumber(String number);
}
