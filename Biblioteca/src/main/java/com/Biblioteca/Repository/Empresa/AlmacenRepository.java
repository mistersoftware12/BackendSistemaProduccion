package com.Biblioteca.Repository.Empresa;

import com.Biblioteca.Models.Empresa.Sucursales.Almacen;
import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AlmacenRepository extends JpaRepository<Almacen ,Long> {

    //Optional<Almacen> findByAlmacenRegistro (AlmacenBodegaTaller almacenBodegaTaller);

    /*

    @Query(value = "SELECT c.id,c.actividades, c.materiales,c.num_participantes, ct.nombre, ct.descripcion, ct.observaciones,ct.lugar\n" +
            "FROM curso c, curso_taller ct \n" +
            "where ct.fecha_inicio IN (:fechaInicio) and ct.id = c.curso_taller_id", nativeQuery = true)
    List<CursoFecha> findByFechaInicio(Date fechaInicio);

    @Transactional
    @Modifying
    @Query(value="delete from cursos_clientes where curso_id=?1 and cliente_id=?2",nativeQuery = true)
    void deleteQuery(Long curso_id,Long cliente_id);
     */
}
