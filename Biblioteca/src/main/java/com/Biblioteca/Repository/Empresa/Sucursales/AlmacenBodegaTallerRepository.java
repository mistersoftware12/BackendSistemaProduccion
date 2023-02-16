package com.Biblioteca.Repository.Empresa.Sucursales;

import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlmacenBodegaTallerRepository extends JpaRepository<AlmacenBodegaTaller,Long> {

    Optional<AlmacenBodegaTaller> findByNombre(String nombre);

}
