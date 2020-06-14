package me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa;

import me.afmiguez.projects.appointmentsdata.entities.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentJPA extends CrudRepository<AppointmentEntity,Long> {
}
