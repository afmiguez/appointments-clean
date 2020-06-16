package me.afmiguez.projects.appointmentsdata.repositories.implementations;


import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProfessorDAOJPATest {

    @Autowired
    private ProfessorDAO professorDAO;


    @Test
    void findByEmail() {

        Professor professor=Professor.builder()
                .firstName("firstname")
                .lastName("lastname")
                .email("email")
                .appointments(new ArrayList<>())
                .availabilities(new ArrayList<>())
                .build();

        assertFalse(professorDAO.findByEmail("email").isPresent());

        professorDAO.save(professor);

        assertTrue(professorDAO.findByEmail("email").isPresent());

        assertFalse(professorDAO.findByEmail("nonExistingEmail").isPresent());
    }
}