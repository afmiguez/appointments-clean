package me.afmiguez.projects.appointmentsdata.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AvailabilityEntity extends BaseEntity{

    @NonNull
    private DayOfWeek dayOfWeek;
    @NonNull
    private LocalTime start;
    @NonNull
    private LocalTime end;

    @NonNull
    @ManyToOne
    @ToString.Exclude
    private ProfessorEntity professor;
}
