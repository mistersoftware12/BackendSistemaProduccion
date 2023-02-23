package com.Biblioteca.Repository.Categoria;

import com.Biblioteca.Models.Categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

    Boolean existsByNombre (String nombre);

}
