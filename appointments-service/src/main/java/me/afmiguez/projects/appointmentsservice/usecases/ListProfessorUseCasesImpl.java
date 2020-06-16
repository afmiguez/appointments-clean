package me.afmiguez.projects.appointmentsservice.usecases;

import lombok.extern.slf4j.Slf4j;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.ListProfessorUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Service
@Slf4j
public class ListProfessorUseCasesImpl implements ListProfessorUseCases {

    private final ProfessorDAO professorDAO;

    @Autowired
    public ListProfessorUseCasesImpl(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }

    @Override
    public Iterable<Professor> listAll() {
        Iterable<Professor> professors=professorDAO.findAll();
        log.info(professors.toString());
        return professors;
    }

    @Override
    public Iterable<Professor> listProfessorByDay(DayOfWeek dayOfWeek) {
        return null;
    }

    @Override
    public Iterable<Professor> listProfessorByStartTime(LocalTime start) {
        return null;
    }

    @Override
    public Iterable<Professor> listProfessorByEndTime(LocalTime end) {
        return null;
    }
}
