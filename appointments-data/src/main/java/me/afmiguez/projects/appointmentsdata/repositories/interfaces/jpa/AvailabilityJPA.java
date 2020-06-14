package me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa;

import me.afmiguez.projects.appointmentsdata.entities.AvailabilityEntity;
import org.springframework.data.repository.CrudRepository;

public interface AvailabilityJPA extends CrudRepository<AvailabilityEntity,Long> {
}
