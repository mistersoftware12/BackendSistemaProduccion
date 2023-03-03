package com.Biblioteca.Repository.Empresa;

import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Models.Persona.Cliente;
import com.Biblioteca.Models.Persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SucursalRepository extends JpaRepository<Sucursal , Long> {





    Optional<Sucursal> findByNombre(String nombre);

    Boolean existsByNombre (String nombre);

}
