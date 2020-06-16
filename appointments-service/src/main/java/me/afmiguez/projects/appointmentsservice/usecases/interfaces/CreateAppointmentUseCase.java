package me.afmiguez.projects.appointmentsservice.usecases.interfaces;

import me.afmiguez.projects.appointmentsdomain.models.Appointment;

public interface CreateAppointmentUseCase {
    Appointment createAppointment(Appointment appointment);
}
