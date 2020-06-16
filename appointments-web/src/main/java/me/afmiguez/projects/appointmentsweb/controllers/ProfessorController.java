package me.afmiguez.projects.appointmentsweb.controllers;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import me.afmiguez.projects.appointmentsdomain.models.Appointment;
import me.afmiguez.projects.appointmentsdomain.models.Availability;
import me.afmiguez.projects.appointmentsdomain.models.Professor;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateProfessorUseCase;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.ListProfessorUseCases;
import me.afmiguez.projects.appointmentsweb.dtos.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/professor")
@Slf4j
public class ProfessorController{

    private final CreateProfessorUseCase createProfessorUseCase;
    private final ListProfessorUseCases listProfessorUseCases;
    private final ModelMapper modelMapper;


    @Autowired
    public ProfessorController(CreateProfessorUseCase createProfessorUseCase,ListProfessorUseCases listProfessorUseCases,ModelMapper modelMapper) {
        this.createProfessorUseCase = createProfessorUseCase;
        this.listProfessorUseCases=listProfessorUseCases;
        this.modelMapper=modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Endpoint to list all Professors", notes = "List all Professors", tags = { "ListAllProfessors" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Professor created success", response= ProfessorDTO.class )
    })
    public ResponseEntity<Iterable<ProfessorDTO>> listAllProfessors(){
        List<ProfessorDTO> professors=new ArrayList<>();
        listProfessorUseCases.listAll().forEach(professor -> professors.add(convertToDTO(professor)));
        return ResponseEntity.ok(professors);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Endpoint to create a new Professor", notes = "Create a new Professor", tags = { "1-CreateProfessor" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Professor created success", response= ProfessorCreateDTO.class )
    })
    public ResponseEntity<ProfessorCreateDTO> createProfessor(@RequestBody ProfessorCreateDTO professorDTO){
        Professor professor=createProfessorUseCase.createProfessor(convertCreateToModel(professorDTO));
        if(null!=professor){
            return ResponseEntity.ok(convertToCreateDTO(professor));
        }
        return ResponseEntity.badRequest().build();
    }

    private ProfessorCreateDTO convertToCreateDTO(Professor professor){
        return modelMapper.map(professor,ProfessorCreateDTO.class);
    }

    private ProfessorDTO convertToDTO(Professor professor){
        ProfessorDTO professorDTO = modelMapper.map(professor, ProfessorDTO.class);
        professorDTO.setAvailability(convertToListAvailabilityDTO(professor.getAvailabilities()));
        professorDTO.setAppointments(convertToListAppointmentDTO(professor.getAppointments()));
        return professorDTO;
    }

    private List<AppointmentSimpleDTO> convertToListAppointmentDTO(List<Appointment> appointments){
        List<AppointmentSimpleDTO> appointmentDTOS=new ArrayList<>();
        appointments.forEach(appointment -> appointmentDTOS.add(modelMapper.map(appointment,AppointmentSimpleDTO.class)));
        return appointmentDTOS;
    }

    private List<AvailabilitySimpleDTO> convertToListAvailabilityDTO(List<Availability> availabilities){
        List<AvailabilitySimpleDTO> availabilitiesDTO=new ArrayList<>();
        availabilities.forEach(availability -> availabilitiesDTO.add(modelMapper.map(availability,AvailabilitySimpleDTO.class)));
        return availabilitiesDTO;
    }

    private Professor convertCreateToModel(ProfessorCreateDTO professorDTO){
        return modelMapper.map(professorDTO,Professor.class);
    }
}
