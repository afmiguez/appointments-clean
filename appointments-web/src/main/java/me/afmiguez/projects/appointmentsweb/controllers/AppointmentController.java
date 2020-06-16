package me.afmiguez.projects.appointmentsweb.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.afmiguez.projects.appointmentsdomain.models.Appointment;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateAppointmentUseCase;
import me.afmiguez.projects.appointmentsweb.dtos.AppointmentCreateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentController(CreateAppointmentUseCase createAppointmentUseCase, ModelMapper modelMapper) {
        this.createAppointmentUseCase = createAppointmentUseCase;
        this.modelMapper = modelMapper;
    }


    @ApiOperation(value = "Endpoint to create new Appointment", notes = "Create new Appointment", tags = { "4-CreateAppointment" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Appointment created success", response= AppointmentCreateDTO.class )
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentCreateDTO> createAppointment(@RequestBody AppointmentCreateDTO appointmentCreateDTO){
        Appointment appointment=createAppointmentUseCase.createAppointment(convertToModel(appointmentCreateDTO));
        if(null!=appointment){
            return ResponseEntity.ok(convertToDTO(appointment));
        }
        return ResponseEntity.badRequest().build();
    }

    private AppointmentCreateDTO convertToDTO(Appointment appointment){
        return modelMapper.map(appointment,AppointmentCreateDTO.class);
    }

    private Appointment convertToModel(AppointmentCreateDTO appointmentCreateDTO){
        return modelMapper.map(appointmentCreateDTO,Appointment.class);
    }
}
