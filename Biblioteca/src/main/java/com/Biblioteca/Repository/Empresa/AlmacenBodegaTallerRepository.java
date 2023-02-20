package com.Biblioteca.Repository.Empresa;

import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlmacenBodegaTallerRepository extends JpaRepository<AlmacenBodegaTaller,Long> {


    @Query(value = "SELECT * FROM almacen a INNER JOIN almacen_bodega_taller c ON a.almacen_bodega_taller_id = c.id where UPPER(c.nombre) = UPPER(:nombre)", nativeQuery = true)
    Optional<AlmacenBodegaTaller> findByNombreAndBusqueda(String nombre);


    /*
    Optional<CursoTaller> findByNombre(String nombre);


    Boolean existsByNombre(String nombre);


    @Query(value = "SELECT * FROM curso_taller ct join taller ta on ta.curso_taller_id= ct.id  where ct.nombre =:nombre and ct.fecha_inicio=:fechaInicio", nativeQuery = true)
    Optional<CursoTaller> findDistinctByNombreAndFechaInicio(String nombre, Date fechaInicio);
     */

}
