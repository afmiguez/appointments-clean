package me.afmiguez.projects.appointmentsdata.entities;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
public class ProfessorEntity extends BaseEntity{
    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @OneToMany(orphanRemoval = true,mappedBy = "professor",cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    private List<AppointmentEntity> appointments;
    @NonNull
    @OneToMany(orphanRemoval = true,mappedBy = "professor",cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    private List<AvailabilityEntity> availabilities;


    public void addAppointment(AppointmentEntity appointmentEntity){
        if(!this.appointments.contains(appointmentEntity)){
            this.appointments.add(appointmentEntity);
            appointmentEntity.setProfessor(this);
        }
    }

    public void addAvailability(AvailabilityEntity availabilityEntity){
        if(!this.availabilities.contains(availabilityEntity)){
            this.availabilities.add(availabilityEntity);
            availabilityEntity.setProfessor(this);
        }
    }
}
