package com.Biblioteca.Models.Empresa;

import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Models.Persona.Cliente;
import com.Biblioteca.Models.Persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SucursalRepository extends JpaRepository<Sucursal , Long> {


    //Optional<Sucursal> findBySucursalLikeIgnoreCase(String sucursal);


}
