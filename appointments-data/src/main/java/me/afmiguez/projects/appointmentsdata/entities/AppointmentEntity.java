package me.afmiguez.projects.appointmentsdata.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AppointmentEntity extends BaseEntity{

//    @NonNull
    @ManyToOne
    @ToString.Exclude
    private StudentEntity student;
    @NonNull
    @ManyToOne
    @ToString.Exclude
    private ProfessorEntity professor;
    @NonNull
    private LocalDateTime startTime;
    @NonNull
    private LocalDateTime endTime;

}
