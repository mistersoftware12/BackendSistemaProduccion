package com.Biblioteca.Repository.Empresa;

import com.Biblioteca.Models.Empresa.Sucursales.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BodegaRepository extends JpaRepository<Bodega,Long> {

    @Query(value = " SELECT * FROM bodega WHERE sucursal_id  = :idSucu", nativeQuery = true)
    List<Bodega> findAllByIdSucursal(Long idSucu);
}
