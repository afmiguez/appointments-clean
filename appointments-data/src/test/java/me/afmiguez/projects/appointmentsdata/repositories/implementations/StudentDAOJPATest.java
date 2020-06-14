package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.entities.StudentEntity;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.StudentDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.config.BootstrapMode;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentDAOJPATest {

    @Autowired
    private StudentDAO studentDAO;

    @Test
    void findByNumber() {
        StudentEntity studentEntity=StudentEntity.builder()
                .appointments(new ArrayList<>())
                .firstName("firstname")
                .lastName("lastname")
                .studentNumber("12345")
                .build();

        studentDAO.save(studentEntity);

        assertTrue(studentDAO.findByNumber("12345").isPresent());
        assertFalse(studentDAO.findByNumber("123456").isPresent());


    }
}