package me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa;

import me.afmiguez.projects.appointmentsdomain.models.Availability;
import org.springframework.data.repository.CrudRepository;

public interface AvailabilityJPA extends CrudRepository<Availability,Long> {
}
