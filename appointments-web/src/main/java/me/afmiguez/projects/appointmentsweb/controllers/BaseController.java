package me.afmiguez.projects.appointmentsweb.controllers;

import lombok.extern.slf4j.Slf4j;
import me.afmiguez.projects.appointmentsdata.repositories.interfaces.BaseDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
@Slf4j
public abstract class BaseController<T, E> {

    private BaseDAO<E,Long> dao;
    protected ModelMapper modelMapper;

    @Autowired
    public BaseController(BaseDAO<E, Long> dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<T>> getAllEntities(){
        Iterable<E> entities=dao.findAll();
        List<T> dtos=new ArrayList<>();
        for(E e :entities){
            dtos.add(convertToDTO(e));
        }
        return ResponseEntity.ok(dtos);
    }



    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getEntityById(@PathVariable("id")Long id){
        Optional<E> entity=dao.findById(id);
        return entity.map(e -> ResponseEntity.ok(Objects.requireNonNull(convertToDTO(e)))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<T> createEntity(@RequestBody T dto){
        Optional<E> optionalEntity=dao.save(convertToEntity(dto));
        return optionalEntity.map(e -> ResponseEntity.ok(Objects.requireNonNull(convertToDTO(e)))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    abstract T convertToDTO(E e);
    abstract E convertToEntity(T t);


}
