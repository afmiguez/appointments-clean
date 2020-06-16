package me.afmiguez.projects.appointmentsservice.usecases.interfaces;

import me.afmiguez.projects.appointmentsdomain.models.Availability;

import java.util.Optional;

public interface CreateAvailabilityUseCase {
    Optional<Availability> createAvailability(Availability availability);
}
