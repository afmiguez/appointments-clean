package me.afmiguez.projects.appointmentsservice.usecases;

import lombok.extern.slf4j.Slf4j;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.AppointmentDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.StudentDAO;
import me.afmiguez.projects.appointmentsdomain.models.Appointment;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import me.afmiguez.projects.appointmentsdomain.models.Student;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateAppointmentUseCase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CreateAppointmentUseCaseImpl implements CreateAppointmentUseCase {

    private final StudentDAO studentDAO;
    private final ProfessorDAO professorDAO;
    private final AppointmentDAO appointmentDAO;

    public CreateAppointmentUseCaseImpl(StudentDAO studentDAO, ProfessorDAO professorDAO, AppointmentDAO appointmentDAO) {
        this.studentDAO = studentDAO;
        this.professorDAO = professorDAO;
        this.appointmentDAO = appointmentDAO;
    }


    @Override
    public Appointment createAppointment(Appointment appointment) {
        log.info("Try to create  a new Appointment");
        if(null!=appointment.getProfessor() && null!=appointment.getStudent()){
            Optional<Professor> optionalProfessor=professorDAO.findByEmail(appointment.getProfessor().getEmail());
            Optional<Student> optionalStudent=studentDAO.findByNumber(appointment.getStudent().getStudentNumber());
            if(optionalProfessor.isPresent()&&optionalStudent.isPresent()){
                log.info("Professor and Student exists in the DB");
                return insertAppointmentIntoDB(optionalProfessor.get(),optionalStudent.get(),appointment);
            }
        }
        return null;
    }

    private Appointment insertAppointmentIntoDB(Professor professor, Student student, Appointment appointment){
        log.info("Check if the Professor can add the Appointment");
        if(professor.canAddAppointment(appointment)){
            log.info("\t\tOK");
            appointment.setProfessor(professor);
            appointment.setStudent(student);

            Optional<Appointment> optionalAppointment=appointmentDAO.save(appointment);
            if(optionalAppointment.isPresent()){
                Appointment newAppointment=optionalAppointment.get();
                professor.addAppointment(newAppointment);
                student.addAppointment(newAppointment);
                professorDAO.save(professor);
                studentDAO.save(student);
                return appointment;
            }
        }
        log.info("\t\tFAIL");
        return null;
    }
}
