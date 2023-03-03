package com.Biblioteca.Repository.Ubicacion;

import com.Biblioteca.Models.Ubicacion.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais,Long> {

    Optional<Pais> findByNombre(String nombre);

    Boolean existsByNombre (String nombre);
}
