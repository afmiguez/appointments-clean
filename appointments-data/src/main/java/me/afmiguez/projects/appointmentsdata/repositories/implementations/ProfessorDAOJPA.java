package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.entities.ProfessorEntity;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa.ProfessorJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProfessorDAOJPA extends BaseDAOJPA<ProfessorEntity,Long> implements ProfessorDAO {

    @Autowired
    public ProfessorDAOJPA(ProfessorJPA professorJPA) {
        super(professorJPA);
    }

    @Override
    public Optional<ProfessorEntity> findByEmail(String email) {
        return ((ProfessorJPA)super.repository).findByEmail(email);
    }

}
