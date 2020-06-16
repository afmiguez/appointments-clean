package me.afmiguez.projects.appointmentsservice.usecases;

import me.afmiguez.projects.appointmentsdata.repositories.interfaces.StudentDAO;
import me.afmiguez.projects.appointmentsdomain.models.Student;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateStudentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateStudentUseCaseImpl implements CreateStudentUseCase {

    private final StudentDAO studentDAO;

    @Autowired
    public CreateStudentUseCaseImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student createStudent(Student student) {
        Optional<Student> optionalStudent=studentDAO.save(student);
        return optionalStudent.orElse(null);
    }
}
