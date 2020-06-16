package me.afmiguez.projects.appointmentsweb.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import me.afmiguez.projects.appointmentsdomain.models.Availability;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateAvailabilityUseCase;
import me.afmiguez.projects.appointmentsweb.dtos.AvailabilityCreateDTO;
import me.afmiguez.projects.appointmentsweb.dtos.ProfessorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/availability")
@Slf4j
public class AvailabilityController {

    private final CreateAvailabilityUseCase availabilityUseCase;
    private final ModelMapper modelMapper;

    @Autowired
    public AvailabilityController(CreateAvailabilityUseCase availabilityUseCase,ModelMapper modelMapper) {
        this.availabilityUseCase = availabilityUseCase;
        this.modelMapper=modelMapper;
    }

    @ApiOperation(value = "Endpoint to create new Availability", notes = "Create new Availability", tags = { "3-CreateAvailability" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Availability created success", response= AvailabilityCreateDTO.class )
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AvailabilityCreateDTO> createAvailability(@RequestBody AvailabilityCreateDTO availabilityCreateDTO){
        Optional<Availability> optionalAvailability=availabilityUseCase.createAvailability(convertToModel(availabilityCreateDTO));
        return optionalAvailability.map(availability -> ResponseEntity.ok(convertToDTO(availability))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private Availability convertToModel(AvailabilityCreateDTO availabilityCreateDTO){
        return modelMapper.map(availabilityCreateDTO,Availability.class);
    }

    private AvailabilityCreateDTO convertToDTO(Availability availability){
        return modelMapper.map(availability,AvailabilityCreateDTO.class);
    }

}
