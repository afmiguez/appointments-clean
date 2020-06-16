package me.afmiguez.projects.appointmentsservice.usecases.interfaces;

import me.afmiguez.projects.appointmentsdomain.models.Professor;

public interface CreateProfessorUseCase {
    Professor createProfessor(Professor professor);
}
