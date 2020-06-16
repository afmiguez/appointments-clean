package me.afmiguez.projects.appointmentsdomain.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends BaseModel {
    @ManyToOne
    @ToString.Exclude
    private Student student;
    @ManyToOne
    @ToString.Exclude
    private Professor professor;
    @NonNull
    private LocalDateTime startTime;
    @NonNull
    private LocalDateTime endTime;


    public boolean overlaps(Appointment other) {
        return !(notOverlaps(other) || other.notOverlaps(other));
    }

    private boolean notOverlaps(Appointment other){
        LocalDateTime start1=this.startTime;
        LocalDateTime end1=this.endTime;
        LocalDateTime start2=other.getStartTime();
        LocalDateTime end2=other.getEndTime();

        return ( (start2.isBefore(start1) && (end2.isBefore(start1) || end2.equals(start1)) )
                        ||
                    ((end1.isBefore(start2) || end1.equals(start2)) && end1.isBefore(end2) )
                );
    }
}
