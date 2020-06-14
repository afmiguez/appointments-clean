package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.entities.StudentEntity;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.StudentDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa.StudentJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudentDAOJPA extends BaseDAOJPA<StudentEntity,Long> implements StudentDAO {

    @Autowired
    public StudentDAOJPA(StudentJPA studentJPA) {
        super(studentJPA);

    }

    @Override
    public Optional<StudentEntity> findByNumber(String number) {
        return ((StudentJPA)super.repository).findByStudentNumber(number);
    }
}
