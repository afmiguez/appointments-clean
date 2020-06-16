package me.afmiguez.projects.appointmentsservice.usecases;

import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateProfessorUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateProfessorUseCaseImpl implements CreateProfessorUseCase {

    private final ProfessorDAO professorDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public CreateProfessorUseCaseImpl(ProfessorDAO professorDAO, ModelMapper modelMapper) {
        this.professorDAO = professorDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public Professor createProfessor(Professor professorCreateDTO) {
        Professor professorEntity=convertToEntity(professorCreateDTO);
        Optional<Professor> optional= professorDAO.save(professorEntity);
        return optional.orElse(null);
    }

    private Professor convertToEntity(Professor professorCreateDTO){
        return modelMapper.map(professorCreateDTO,Professor.class);
    }
}
