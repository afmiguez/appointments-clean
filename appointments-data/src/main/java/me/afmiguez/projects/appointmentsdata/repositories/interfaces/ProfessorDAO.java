package me.afmiguez.projects.appointmentsdata.repositories.interfaces;

import me.afmiguez.projects.appointmentsdata.entities.ProfessorEntity;

import java.util.Optional;

public interface ProfessorDAO extends BaseDAO<ProfessorEntity,Long> {
    Optional<ProfessorEntity> findByEmail(String email);
}
