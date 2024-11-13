package com.prueba.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.api.entities.Nave;
import com.prueba.api.services.INaveService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/naves")
public class NaveController {

    @Autowired
    private INaveService naveService;

    @GetMapping
    public Page<Nave> getAllNaves(Pageable pageable) {
        return naveService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nave> naveById(@PathVariable Long id) {
        return naveService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name")
    public List<Nave> naveLikeName(@RequestParam String name) {
        return naveService.findByNameContaining(name);
    }

    @PostMapping
    public Nave createSpaceship(@RequestBody Nave nave) {
        return naveService.save(nave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nave> update(@PathVariable Long id, @RequestBody Nave nave) {

        Optional<Nave> naveOptional = naveService.update(id, nave);

        if (naveOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(naveOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        
        if (naveService.findById(id).isPresent()) {
            naveService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
