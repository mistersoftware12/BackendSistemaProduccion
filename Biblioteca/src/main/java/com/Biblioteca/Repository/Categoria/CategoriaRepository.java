package com.Biblioteca.Repository.Categoria;

import com.Biblioteca.Models.Categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

    Boolean existsByNombre (String nombre);



    @Query(value = "SELECT * FROM categoria WHERE estado = :estado", nativeQuery = true)
    List<Categoria> findByEstado(boolean estado);


}
