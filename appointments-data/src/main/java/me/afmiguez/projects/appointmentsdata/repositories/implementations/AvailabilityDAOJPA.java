package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.repositories.interfaces.AvailabilityDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa.AvailabilityJPA;
import me.afmiguez.projects.appointmentsdomain.models.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AvailabilityDAOJPA extends BaseDAOJPA<Availability,Long> implements AvailabilityDAO {

    @Autowired
    public AvailabilityDAOJPA(AvailabilityJPA availabilityJPA) {
        super(availabilityJPA);
    }

}
