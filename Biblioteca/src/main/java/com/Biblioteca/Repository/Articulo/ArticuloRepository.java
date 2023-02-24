package com.Biblioteca.Repository.Articulo;

import com.Biblioteca.Models.Articulo.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticuloRepository extends JpaRepository<Articulo,Long> {

    Optional<Articulo> findByNombre(String nombre);
        Boolean existsByNombre (String nombre);

}
