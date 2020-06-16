package me.afmiguez.projects.appointmentsweb.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class AvailabilitySimpleDTO {

    private final static String pattern="HH:mm:ss";

    @ApiModelProperty(example = "THURSDAY")
    private DayOfWeek dayOfWeek;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = pattern)
    @ApiModelProperty(example = "08:00:00")
    private LocalTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = pattern)
    @ApiModelProperty(example = "12:00:00")
    private LocalTime end;
}
