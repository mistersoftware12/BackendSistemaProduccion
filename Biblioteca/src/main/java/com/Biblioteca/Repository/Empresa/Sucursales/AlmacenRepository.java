package com.Biblioteca.Repository.Empresa.Sucursales;

import com.Biblioteca.Models.Empresa.Sucursales.Almacen;
import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlmacenRepository extends JpaRepository<Almacen,Long> {

    Optional<Almacen> findByAlmacenBodegaTaller(AlmacenBodegaTaller almacenBodegaTaller);

}
