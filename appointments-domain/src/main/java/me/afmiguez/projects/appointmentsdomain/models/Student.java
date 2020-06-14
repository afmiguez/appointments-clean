package me.afmiguez.projects.appointmentsdomain.models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Builder
@Data
public class Student {
    @NonNull
    private String studentNumber;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private List<Appointment> appointments;
}
