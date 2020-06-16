package me.afmiguez.projects.appointmentsweb.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class StudentCreateDTO {


    @ApiModelProperty(example = "12345")
    private String studentNumber;
    @ApiModelProperty(example = "lastname")
    private String firstName;
    @ApiModelProperty(example = "firstname")
    private String lastName;

}
