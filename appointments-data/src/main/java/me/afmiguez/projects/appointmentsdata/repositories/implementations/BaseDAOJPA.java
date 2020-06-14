package me.afmiguez.projects.appointmentsdata.repositories.implementations;

import me.afmiguez.projects.appointmentsdata.repositories.interfaces.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract class BaseDAOJPA<T,K> implements BaseDAO<T,K> {

    protected CrudRepository<T,K> repository;

    @Autowired
    public BaseDAOJPA(CrudRepository<T, K> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> save(T t) {
        return Optional.of(repository.save(t));
    }

    @Override
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(K k) {
        return repository.findById(k);
    }

    @Override
    public void remove(T t) {
        repository.delete(t);
    }

    @Override
    public void removeById(K k) {
        repository.deleteById(k);
    }
}
