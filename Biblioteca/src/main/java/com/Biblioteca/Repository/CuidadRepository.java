package com.Biblioteca.Repository;

import com.Biblioteca.Models.Cuidad.Cuidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuidadRepository extends JpaRepository<Cuidad,Long> {

    Optional<Cuidad> findByNombre(String nombre);

    Boolean existsByNombre (String nombre);
}
