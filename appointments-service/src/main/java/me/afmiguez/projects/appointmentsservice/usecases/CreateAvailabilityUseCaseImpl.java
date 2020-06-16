package me.afmiguez.projects.appointmentsservice.usecases;

import lombok.extern.slf4j.Slf4j;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdomain.models.Availability;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateAvailabilityUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CreateAvailabilityUseCaseImpl implements CreateAvailabilityUseCase {

    private final ProfessorDAO professorDAO;
    private final ModelMapper modelMap;

    @Autowired
    public CreateAvailabilityUseCaseImpl(ProfessorDAO professorDAO,ModelMapper modelMap) {
        this.professorDAO = professorDAO;
        this.modelMap=modelMap;
    }

    @Override
    public Optional<Availability> createAvailability(Availability availabilityDTO) {
        if(null == availabilityDTO.getProfessor() || null==availabilityDTO.getProfessor().getEmail()) {
            return Optional.empty();
        }

        Optional<Professor> optionalProfessorFromDB= professorDAO.findByEmail(availabilityDTO.getProfessor().getEmail());
        if(optionalProfessorFromDB.isPresent()){
            Professor professorFromDB=optionalProfessorFromDB.get();
            Availability availability=convertToModel(availabilityDTO);
            professorFromDB.addAvailability(availability);
            professorDAO.save(professorFromDB);
            return Optional.of(availability);
        }
        return Optional.empty();
    }

    private Availability convertToModel(Availability availabilityCreateDTO){
        return modelMap.map(availabilityCreateDTO,Availability.class);
    }
}
