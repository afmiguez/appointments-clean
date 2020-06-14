package me.afmiguez.projects.appointmentsdomain.models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Builder
@Data
public class Availability {
    @NonNull
    private DayOfWeek dayOfWeek;
    @NonNull
    private LocalTime start;
    @NonNull
    private LocalTime end;


    public boolean canSchedule(Appointment appointment){
        DayOfWeek appointmentDay=appointment.getStartTime().getDayOfWeek();

        if(appointmentDay.equals(dayOfWeek)){
            return contains(appointment);
        }
        return false;
    }

    private boolean contains(Appointment appointment){
        LocalTime appointmentStartTime=appointment.getStartTime().toLocalTime();
        LocalTime appointmentEndTime=appointment.getEndTime().toLocalTime();
        return (this.start.isBefore(appointmentStartTime) || this.start.equals(appointmentStartTime))
                &&
                (this.end.isAfter(appointmentEndTime) || this.end.equals(appointmentEndTime));
    }
}
