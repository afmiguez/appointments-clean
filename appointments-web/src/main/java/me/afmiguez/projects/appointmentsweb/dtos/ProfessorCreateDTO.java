package me.afmiguez.projects.appointmentsweb.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ProfessorCreateDTO {

    @ApiModelProperty(example = "abc@def.org")
    private String email;
    @ApiModelProperty(example = "firstName")
    private String firstName;
    @ApiModelProperty(example = "lastName")
    private String lastName;
}

