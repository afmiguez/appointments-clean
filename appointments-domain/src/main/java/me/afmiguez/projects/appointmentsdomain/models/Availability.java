package me.afmiguez.projects.appointmentsdomain.models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Availability extends BaseModel {
    @NonNull
    private DayOfWeek dayOfWeek;
    @NonNull
    private LocalTime start;
    @NonNull
    private LocalTime end;
    @NonNull
    @ManyToOne
    @ToString.Exclude
    private Professor professor;


    public boolean canSchedule(Appointment appointment){
        log.debug("Check if can schedule a appointment");
        DayOfWeek appointmentDay=appointment.getStartTime().getDayOfWeek();

        if(appointmentDay.equals(dayOfWeek)){
            log.debug("Availability and Appointment are in the same DayOfWeek");
            return contains(appointment);
        }
        return false;
    }

    private boolean contains(Appointment appointment){
        LocalTime appointmentStartTime=appointment.getStartTime().toLocalTime();
        LocalTime appointmentEndTime=appointment.getEndTime().toLocalTime();
        log.debug("Availability start should be before or at same time as Appointment Start AND Availability end should be after or at same time as Appointment End");
        return (this.start.isBefore(appointmentStartTime) || this.start.equals(appointmentStartTime))
                &&
                (this.end.isAfter(appointmentEndTime) || this.end.equals(appointmentEndTime));
    }
}
