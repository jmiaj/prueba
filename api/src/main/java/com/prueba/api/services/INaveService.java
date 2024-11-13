package com.prueba.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prueba.api.entities.Nave;

public interface INaveService {

    Page<Nave> findAll(Pageable pageable);

    Optional<Nave> findById(Long id);

    List<Nave> findByNameContaining(String name);

    Nave save(Nave product);
    
    Optional<Nave> update(Long id, Nave product);

    void delete(Long id);

}
