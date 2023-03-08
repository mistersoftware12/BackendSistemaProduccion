package com.Biblioteca.Repository.Servicio;

import com.Biblioteca.Models.Servicio.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    Optional<Servicio> findByNombre(String nombre);

    Boolean existsByNombre (String nombre);

}
