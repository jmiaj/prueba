package com.prueba.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba.api.entities.Nave;

public interface INaveRepository extends JpaRepository<Nave, Long> {

    @Query("select n from Nave n where n.name like %:name%")
    List<Nave> findOneLikeName(@Param("name") String nombre);

}
