package me.afmiguez.projects.appointmentsdomain.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends BaseModel {

    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @OneToMany(orphanRemoval = true,mappedBy = "professor",cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    private List<Appointment> appointments=new ArrayList<>();
    @NonNull
    @OneToMany(orphanRemoval = true,mappedBy = "professor",cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    private List<Availability> availabilities=new ArrayList<>();

    public List<Availability> getAvailabilities(){
        return Collections.unmodifiableList(availabilities);
    }

    public List<Appointment> getAppointments(){
        return Collections.unmodifiableList(appointments);
    }

    public void addAvailability(Availability availability){
        if(!availabilities.contains(availability)){
            availabilities.add(availability);
            availability.setProfessor(this);
        }
    }

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        appointment.setProfessor(this);
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

    public boolean canAddAppointment(Appointment appointment) {
        return canSchedule(appointment) && !alreadyHaveAppointment(appointment);
    }
}
