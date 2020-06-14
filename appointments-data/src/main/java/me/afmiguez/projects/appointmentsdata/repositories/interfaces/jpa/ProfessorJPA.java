package me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa;

import me.afmiguez.projects.appointmentsdata.entities.ProfessorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfessorJPA extends CrudRepository<ProfessorEntity,Long> {
    Optional<ProfessorEntity> findByEmail(String email);
}
