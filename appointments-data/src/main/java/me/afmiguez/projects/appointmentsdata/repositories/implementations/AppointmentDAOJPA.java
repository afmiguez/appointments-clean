package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.repositories.interfaces.AppointmentDAO;
import me.afmiguez.projects.appointmentsdomain.models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentDAOJPA extends BaseDAOJPA<Appointment,Long> implements AppointmentDAO {

    @Autowired
    public AppointmentDAOJPA(CrudRepository<Appointment, Long> repository) {
        super(repository);
    }

}
