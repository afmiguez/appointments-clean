package me.afmiguez.projects.appointmentsdata.repositories.interfaces.jpa;

import me.afmiguez.projects.appointmentsdomain.models.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentJPA extends CrudRepository<Appointment,Long> {
}
