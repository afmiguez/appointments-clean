package me.afmiguez.projects.appointmentsweb.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class StudentSimpleDTO {

    @ApiModelProperty(example = "12345")
    private String studentNumber;
    @ApiModelProperty(example = "lastname")
    private String firstName;
    @ApiModelProperty(example = "firstname")
    private String lastName;
    private List<AppointmentDTO> appointments;
}
