package me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa;

import me.afmiguez.projects.appointmentsdomain.models.Professor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfessorJPA extends CrudRepository<Professor,Long> {
    Optional<Professor> findByEmail(String email);
}
