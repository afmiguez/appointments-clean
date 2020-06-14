package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.entities.AvailabilityEntity;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.AvailabilityDAO;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa.AvailabilityJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AvailabilityDAOJPA extends BaseDAOJPA<AvailabilityEntity,Long> implements AvailabilityDAO {

    @Autowired
    public AvailabilityDAOJPA(AvailabilityJPA availabilityJPA) {
        super(availabilityJPA);
    }

}
