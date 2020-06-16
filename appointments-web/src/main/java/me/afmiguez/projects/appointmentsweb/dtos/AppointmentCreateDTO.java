package me.afmiguez.projects.appointmentsweb.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel
public class AppointmentCreateDTO {

    private final static String pattern="yyyy-MM-dd HH:mm:ss";

    private StudentSimpleDTO student;
    private ProfessorCreateDTO professor;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = pattern)
    @ApiModelProperty(example = "1970-01-01 08:00:00")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = pattern)
    @ApiModelProperty(example = "1970-01-01 08:00:00")
    private LocalDateTime endTime;
}
