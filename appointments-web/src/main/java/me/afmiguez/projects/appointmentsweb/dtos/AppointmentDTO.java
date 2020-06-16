package me.afmiguez.projects.appointmentsweb.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AppointmentDTO {

    private final static String pattern="yyyy-MM-dd HH:mm:ss";

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = pattern)
    @ApiModelProperty(example = "1970-01-01 08:00:00")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = pattern)
    @ApiModelProperty(example = "1970-01-01 08:00:00")
    private LocalDateTime endTime;
}
