package com.Biblioteca.Repository.Empresa;

import com.Biblioteca.Models.Empresa.Sucursales.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlmacenRepository extends JpaRepository<Almacen ,Long> {



    @Query(value = " SELECT * FROM almacen WHERE sucursal_id  = :idSucu", nativeQuery = true)
    List<Almacen> findAllByIdSucursal(Long idSucu);

}
