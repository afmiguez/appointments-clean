package me.afmiguez.projects.appointmentsdomain.models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Data
public class Appointment {
    @NonNull
    private Student student;
    @NonNull
    private Professor professor;
    @NonNull
    private LocalDateTime startTime;
    @NonNull
    private LocalDateTime endTime;

    /**
     * Check if appointments times overlaps. Must be commutative
     * @param other
     * @return
     */
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
