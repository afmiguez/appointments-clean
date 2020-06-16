package me.afmiguez.projects.appointmentsweb.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.StudentDAO;
import me.afmiguez.projects.appointmentsdomain.models.Student;
import me.afmiguez.projects.appointmentsservice.usecases.interfaces.CreateStudentUseCase;
import me.afmiguez.projects.appointmentsweb.dtos.ProfessorDTO;
import me.afmiguez.projects.appointmentsweb.dtos.StudentCreateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
@Slf4j
public class StudentController {

    private final ModelMapper modelMapper;
    private final CreateStudentUseCase createStudentUseCase;

    public StudentController(ModelMapper modelMapper,CreateStudentUseCase createStudentUseCase) {
        this.modelMapper = modelMapper;
        this.createStudentUseCase=createStudentUseCase;
    }


    @ApiOperation(value = "Endpoint to create new Student", notes = "Create new Student", tags = { "2-CreateStudent" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student created success", response= StudentCreateDTO.class )
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentCreateDTO> createStudent(@RequestBody StudentCreateDTO studentCreateDTO){
        Student student=createStudentUseCase.createStudent(convertToEntity(studentCreateDTO));
        if(null!=student){
            return ResponseEntity.ok(convertToDTO(student));
        }
        return ResponseEntity.badRequest().build();
    }

    private StudentCreateDTO convertToDTO(Student student){
        return modelMapper.map(student,StudentCreateDTO.class);
    }

    private Student convertToEntity(StudentCreateDTO studentCreateDTO) {
        Student studentEntity= modelMapper.map(studentCreateDTO,Student.class);
        log.debug(studentEntity.toString());
        return studentEntity;
    }
}
