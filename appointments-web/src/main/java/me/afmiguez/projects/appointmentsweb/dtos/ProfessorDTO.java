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
public class ProfessorDTO {

    @ApiModelProperty(example = "abc@def.org")
    private String email;
    @ApiModelProperty(example = "firstName")
    private String firstName;
    @ApiModelProperty(example = "lastname")
    private String lastName;
    private List<AvailabilitySimpleDTO> availability;
    private List<AppointmentSimpleDTO> appointments;

}
