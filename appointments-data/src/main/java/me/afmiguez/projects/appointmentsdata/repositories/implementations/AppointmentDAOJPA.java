package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.entities.AppointmentEntity;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.AppointmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentDAOJPA extends BaseDAOJPA<AppointmentEntity,Long> implements AppointmentDAO {

    @Autowired
    public AppointmentDAOJPA(CrudRepository<AppointmentEntity, Long> repository) {
        super(repository);
    }

}
