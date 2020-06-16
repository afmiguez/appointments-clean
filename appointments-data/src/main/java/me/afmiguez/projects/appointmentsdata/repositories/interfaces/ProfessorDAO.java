package me.afmiguez.projects.appointmentsdata.repositories.interfaces;

import me.afmiguez.projects.appointmentsdomain.models.Professor;

import java.util.Optional;

public interface ProfessorDAO extends BaseDAO<Professor,Long> {
    Optional<Professor> findByEmail(String email);
}
