package com.prueba.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prueba.api.entities.Nave;
import com.prueba.api.repositories.INaveRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class NaveServiceImp implements INaveService {

    @Autowired
    private INaveRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<Nave> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "naves", unless="#result == null")
    public Optional<Nave> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nave> findByNameContaining(String name) {
        return repository.findOneLikeName(name);
    }

    @Override
    @Transactional
    public Nave save(Nave nave) {
        return repository.save(nave);
    }

    @Override
    @Transactional
    public Optional<Nave> update(Long id, Nave nave) {
        Optional<Nave> naveOptional = repository.findById(id);

        if (naveOptional.isPresent()) {
            Nave naveDb = naveOptional.orElseThrow();
            naveDb.setName(nave.getName());

            return Optional.of(repository.save(naveDb));
        }

        return naveOptional;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Nave> naveOptional = repository.findById(id);

        if (naveOptional.isPresent()) {
            Nave naveDb = naveOptional.orElseThrow();

            repository.delete(naveDb);
        }
    }

}
