package me.afmiguez.projects.appointmentsservice.usecases;

import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateProfessorUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateProfessorUseCaseImpl implements CreateProfessorUseCase {

    private final ProfessorDAO professorDAO;

    @Autowired
    public CreateProfessorUseCaseImpl(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }

    @Override
    public Professor createProfessor(Professor professor) {
        Optional<Professor> optional= professorDAO.save(professor);
        return optional.orElse(null);
    }

}
