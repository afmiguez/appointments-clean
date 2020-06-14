package me.afmiguez.projects.appointmentsdata.repositories.interfaces;

import me.afmiguez.projects.appointmentsdata.entities.StudentEntity;

import java.util.Optional;

public interface BaseDAO<T,K> {

    Optional<T> save(T t);
    Iterable<T> findAll();
    Optional<T> findById(K k);
    void remove(T t);
    void removeById(K k);

}
