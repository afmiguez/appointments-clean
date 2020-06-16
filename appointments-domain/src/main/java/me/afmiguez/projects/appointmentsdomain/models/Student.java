package me.afmiguez.projects.appointmentsdomain.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseModel {

    @NonNull
    private String studentNumber;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @OneToMany(orphanRemoval = true,mappedBy = "student",cascade = CascadeType.PERSIST)
    private List<Appointment> appointments;

    public void addAppointment(Appointment appointmentEntity){
        if(!this.appointments.contains(appointmentEntity)){
            this.appointments.add(appointmentEntity);
            appointmentEntity.setStudent(this);
        }
    }
}
