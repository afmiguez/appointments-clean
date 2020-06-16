package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.repositories.interfaces.AppointmentDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.AvailabilityDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.ProfessorDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.StudentDAO;
import me.afmiguez.projects.appointmentsdomain.models.Appointment;
import me.afmiguez.projects.appointmentsdomain.models.Availability;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import me.afmiguez.projects.appointmentsdomain.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
        Professor professor= Professor.builder()
                .firstName("firstname")
                .lastName("lastname")
                .email("email")
                .availabilities(new ArrayList<>())
                .appointments(new ArrayList<>())
                .build();

        professor=professorDAO.save(professor).orElse(null);

        assertNotNull(professor);


        Student student1= Student.builder()
                .firstName("firstname")
                .lastName("lastname")
                .studentNumber("12345")
                .appointments(new ArrayList<>())
                .build();

        student1=studentDAO.save(student1).orElse(null);

        assertNotNull(student1);

        Student student2= Student.builder()
                .firstName("firstname")
                .lastName("lastname")
                .studentNumber("12346")
                .appointments(new ArrayList<>())
                .build();

        student2=studentDAO.save(student2).orElse(null);
        assertNotNull(student2);

        Availability availability=Availability.builder()
                .dayOfWeek(LocalDate.now().getDayOfWeek())
                .start(LocalTime.of(8,0))
                .end(LocalTime.of(20,0))
                .professor(professor)
                .build();



        Appointment appointment1=Appointment.builder()
                .student(student1)
                .professor(professor)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(30))
                .build();

        student1.addAppointment(appointment1);

        Appointment appointment2=Appointment.builder()
                .student(student2)
                .professor(professor)
                .startTime(LocalDateTime.now().plusHours(1))
                .endTime(LocalDateTime.now().plusHours(1).plusMinutes(30))
                .build();

        student2.addAppointment(appointment2);

        professor.addAvailability(availability);
        professor.addAppointment(appointment1);
        professor.addAppointment(appointment2);

        System.out.println(professor);
        professorDAO.save(professor);

        assertEquals(1,((List<Professor>)professorDAO.findAll()).size());

        Iterable<Appointment> allAppointments=this.appointmentDAO.findAll();
        System.out.println(allAppointments);
//        assertEquals(2,((List<Appointment>) allAppointments).size());

        Professor professorFromDB=this.professorDAO.findByEmail("email").orElse(null);
        assertNotNull(professorFromDB);
        assertEquals(1,professor.getAvailabilities().size());
        assertEquals(2,professor.getAppointments().size());

        Student studentFromDB=this.studentDAO.findByNumber("12345").orElse(null);
        assertNotNull(studentFromDB);

        assertEquals(1,studentFromDB.getAppointments().size());


    }
}
