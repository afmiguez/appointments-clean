package me.afmiguez.projects.appointmentsservice.usecases;

import lombok.extern.slf4j.Slf4j;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdomain.models.Availability;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateAvailabilityUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CreateAvailabilityUseCaseImpl implements CreateAvailabilityUseCase {

    private final ProfessorDAO professorDAO;

    @Autowired
    public CreateAvailabilityUseCaseImpl(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }

    @Override
    public Optional<Availability> createAvailability(Availability availability) {
        if(null == availability.getProfessor() || null==availability.getProfessor().getEmail()) {
            return Optional.empty();
        }

        Optional<Professor> optionalProfessorFromDB= professorDAO.findByEmail(availability.getProfessor().getEmail());
        if(optionalProfessorFromDB.isPresent()){
            Professor professorFromDB=optionalProfessorFromDB.get();
            professorFromDB.addAvailability(availability);
            professorDAO.save(professorFromDB);
            return Optional.of(availability);
        }
        return Optional.empty();
    }

}
