package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.entities.AppointmentEntity;
import me.afmiguez.projects.appointmentsdata.entities.AvailabilityEntity;
import me.afmiguez.projects.appointmentsdata.entities.ProfessorEntity;
import me.afmiguez.projects.appointmentsdata.entities.StudentEntity;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.AppointmentDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.AvailabilityDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.StudentDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class SchemaTest {

    @Autowired
    private ProfessorDAO professorDAO;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private AvailabilityDAO availabilityDAO;
    @Autowired
    private AppointmentDAO appointmentDAO;

    @Test
    void testSchema(){
        ProfessorEntity professorEntity= ProfessorEntity.builder()
                .firstName("firstname")
                .lastName("lastname")
                .email("email")
                .availabilities(new ArrayList<>())
                .appointments(new ArrayList<>())
                .build();

        professorEntity=professorDAO.save(professorEntity).orElse(null);

        assertNotNull(professorEntity);


        StudentEntity studentEntity1= StudentEntity.builder()
                .firstName("firstname")
                .lastName("lastname")
                .studentNumber("12345")
                .appointments(new ArrayList<>())
                .build();

        studentEntity1=studentDAO.save(studentEntity1).orElse(null);

        assertNotNull(studentEntity1);

        StudentEntity studentEntity2= StudentEntity.builder()
                .firstName("firstname")
                .lastName("lastname")
                .studentNumber("12346")
                .appointments(new ArrayList<>())
                .build();

        studentEntity2=studentDAO.save(studentEntity2).orElse(null);
        assertNotNull(studentEntity2);

        AvailabilityEntity availabilityEntity=AvailabilityEntity.builder()
                .dayOfWeek(LocalDate.now().getDayOfWeek())
                .start(LocalTime.of(8,0))
                .end(LocalTime.of(20,0))
                .professor(professorEntity)
                .build();



        AppointmentEntity appointmentEntity1=AppointmentEntity.builder()
                .student(studentEntity1)
                .professor(professorEntity)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(30))
                .build();

        studentEntity1.addAppointment(appointmentEntity1);

        AppointmentEntity appointmentEntity2=AppointmentEntity.builder()
                .student(studentEntity2)
                .professor(professorEntity)
                .startTime(LocalDateTime.now().plusHours(1))
                .endTime(LocalDateTime.now().plusHours(1).plusMinutes(30))
                .build();

        studentEntity2.addAppointment(appointmentEntity2);

        professorEntity.addAvailability(availabilityEntity);
        professorEntity.addAppointment(appointmentEntity1);
        professorEntity.addAppointment(appointmentEntity2);

        System.out.println(professorEntity);
        professorDAO.save(professorEntity);

        assertEquals(1,((List<ProfessorEntity>)professorDAO.findAll()).size());

        Iterable<AppointmentEntity> allAppointments=this.appointmentDAO.findAll();
        System.out.println(allAppointments);
        assertEquals(2,((List<AppointmentEntity>) allAppointments).size());

        ProfessorEntity professorEntityFromDB=this.professorDAO.findByEmail("email").orElse(null);
        assertNotNull(professorEntityFromDB);
        assertEquals(1,professorEntity.getAvailabilities().size());
        assertEquals(2,professorEntity.getAppointments().size());

        StudentEntity studentEntityFromDB=this.studentDAO.findByNumber("12345").orElse(null);
        assertNotNull(studentEntityFromDB);

        assertEquals(1,studentEntityFromDB.getAppointments().size());


    }
}
