package com.Biblioteca.Repository.Persona;
import com.Biblioteca.Models.Persona.Persona;
import com.Biblioteca.Models.Persona.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {

    Optional<Proveedor> findByPersona (Persona persona);



}
