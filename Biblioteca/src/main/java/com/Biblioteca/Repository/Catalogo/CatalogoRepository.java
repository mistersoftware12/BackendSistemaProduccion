package com.Biblioteca.Repository.Catalogo;

import com.Biblioteca.Models.Catalogo.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {


    Optional<Catalogo> findByNombre(String nombre);

    Boolean existsByNombre (String nombre);

    @Query(value = "SELECT * FROM catalogo WHERE estado = :estado", nativeQuery = true)
    List<Catalogo> findByEstado(boolean estado);
}
