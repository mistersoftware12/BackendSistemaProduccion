package com.Biblioteca.Repository.Articulo;


import com.Biblioteca.Models.Articulo.Articulo;
import com.Biblioteca.Models.Articulo.ArticuloProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ArticuloProveedorRepository extends JpaRepository<ArticuloProveedor,Long> {

    @Query(value = "SELECT * FROM articulo_proveedor WHERE articulo_id = :idArticulo", nativeQuery = true)
    Optional<ArticuloProveedor> findByArticuloId(Long idArticulo);




}
