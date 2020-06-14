package me.afmiguez.projects.appointmentsdata.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
public class StudentEntity extends BaseEntity{
    @NonNull
    private String studentNumber;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @OneToMany(orphanRemoval = true,mappedBy = "student")
    private List<AppointmentEntity> appointments;

    public void addAppointment(AppointmentEntity appointmentEntity){
        if(!this.appointments.contains(appointmentEntity)){
            this.appointments.add(appointmentEntity);
            appointmentEntity.setStudent(this);
        }
    }
}
