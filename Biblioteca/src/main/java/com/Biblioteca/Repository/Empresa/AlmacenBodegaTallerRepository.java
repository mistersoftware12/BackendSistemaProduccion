package com.Biblioteca.Repository.Empresa;

import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlmacenBodegaTallerRepository extends JpaRepository<AlmacenBodegaTaller,Long> {


    @Query(value = "SELECT * FROM almacen a INNER JOIN almacen_bodega_taller c ON a.almacen_bodega_taller_id = c.id where UPPER(c.nombre) = UPPER(:nombre)", nativeQuery = true)
    Optional<AlmacenBodegaTaller> findByNombreAndBusquedaAlmacen(String nombre);

    @Query(value = "SELECT * FROM bodega a INNER JOIN almacen_bodega_taller c ON a.almacen_bodega_taller_id = c.id where UPPER(c.nombre) = UPPER(:nombre)", nativeQuery = true)
    Optional<AlmacenBodegaTaller> findByNombreAndBusquedaBodega(String nombre);

    @Query(value = "SELECT * FROM taller a INNER JOIN almacen_bodega_taller c ON a.almacen_bodega_taller_id = c.id where UPPER(c.nombre) = UPPER(:nombre)", nativeQuery = true)
    Optional<AlmacenBodegaTaller> findByNombreAndBusquedaTaller(String nombre);


}
