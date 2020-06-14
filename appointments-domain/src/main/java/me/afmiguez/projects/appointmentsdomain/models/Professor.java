package me.afmiguez.projects.appointmentsdomain.models;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Builder
@Data
public class Professor {

    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private List<Appointment> appointments;
    @NonNull
    private List<Availability> availabilities;

    public List<Availability> getAvailabilities(){
        return Collections.unmodifiableList(availabilities);
    }

    public List<Appointment> getAppointments(){
        return Collections.unmodifiableList(appointments);
    }

    public void addAvailability(Availability availability){
        if(!availabilities.contains(availability)){
            availabilities.add(availability);
        }
    }

    public void addAppointment(Appointment appointment){
        if(canSchedule(appointment) && !alreadyHaveAppointment(appointment)){
            appointments.add(appointment);
            appointment.setProfessor(this);
        }
    }

    private boolean alreadyHaveAppointment(Appointment newAppointment){
        for(Appointment existingAppointment:appointments){
            if(newAppointment.overlaps(existingAppointment)){
                return true;
            }
        }
        return false;
    }

    private boolean canSchedule(Appointment appointment){
        for(Availability availability:availabilities){
            if(availability.canSchedule(appointment)){
                return true;
            }
        }
        return false;
    }
}
