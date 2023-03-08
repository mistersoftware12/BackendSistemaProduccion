package com.Biblioteca.Service.Trasporte;

import com.Biblioteca.DTO.Extra.MaximoDatoResponse;
import com.Biblioteca.DTO.Servicio.ServicioRequest;
import com.Biblioteca.DTO.Servicio.ServicioResponse;
import com.Biblioteca.DTO.Transporte.TransporteRequest;
import com.Biblioteca.DTO.Transporte.TransporteResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Servicio.Servicio;
import com.Biblioteca.Models.Transporte.Transporte;
import com.Biblioteca.Repository.Servicio.ServicioRepository;
import com.Biblioteca.Repository.Transporte.TrasporteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrasporteService {
    @Autowired
    private TrasporteRepository trasporteRepository;


    public boolean regitrarTransporte (TransporteRequest transporteRequest){


        if(contarNombre( transporteRequest.getNombre()) == BigInteger.valueOf(0)){

            Transporte newTrasporte = new Transporte();


            newTrasporte.setNombre(transporteRequest.getNombre());
            newTrasporte.setEstado(transporteRequest.isEstado());
            newTrasporte.setDescripcion(transporteRequest.getDescripcion());


            try {
                trasporteRepository.save(newTrasporte);
                return true;
            }catch (Exception e){
                throw new BadRequestException("No se registró el trasporte" +e);
            }
        }else {
            throw new BadRequestException("Ya existe una transporte con ese nombre");
        }


    }


    public List<TransporteResponse> listAllTrasporte() {

        List<Transporte> transportes = trasporteRepository.findAll();


        return transportes.stream().map(transporteRequest->{
            TransporteResponse response = new TransporteResponse();


            response.setId(transporteRequest.getId());
            response.setNombre(transporteRequest.getNombre());
            response.setEstado(transporteRequest.isEstado());
            response.setDescripcion(transporteRequest.getDescripcion());

            if(transporteRequest.isEstado() == true){
                response.setNombreEstado("Activo");
            }

            if(transporteRequest.isEstado() == false){
                response.setNombreEstado("Inactivo");
            }

            return response;
        }).collect(Collectors.toList());
    }


    public TransporteResponse transporteById(Long id){

        TransporteResponse response = new TransporteResponse();

        Optional<Transporte> transporteRequest = trasporteRepository.findById(id);

        if(transporteRequest.isPresent()){

            response.setId(transporteRequest.get().getId());
            response.setNombre(transporteRequest.get().getNombre());
            response.setEstado(transporteRequest.get().isEstado());
            response.setDescripcion(transporteRequest.get().getDescripcion());


            if(transporteRequest.get().isEstado() == true){
                response.setNombreEstado("Si");
            }

            if(transporteRequest.get().isEstado() == false){
                response.setNombreEstado("No");
            }


            return response;
        }else{
            throw new BadRequestException("No existe un trasporte con id seleccionado");
        }

    }

    @Transactional
    public boolean actualizardatosTransporte(TransporteRequest transporteRequest  ){

        Optional<Transporte> transporte = trasporteRepository.findById(transporteRequest.getId());

        if(transporte.isPresent()){

            transporte.get().setNombre(transporteRequest.getNombre());
            transporte.get().setEstado(transporteRequest.isEstado());
            transporte.get().setDescripcion(transporteRequest.getDescripcion());
            try{

                trasporteRepository.save(transporte.get());
                return true;
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizo" + ex);
            }
        } else {
            throw new BadRequestException("No existe un trasporte con id "+transporteRequest.getId() );
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    public BigInteger contarNombre(String nombre) {
        Query nativeQuery = entityManager.createNativeQuery("SELECT count(id) FROM catalogo WHERE UPPER(nombre) = UPPER(?)");
        nativeQuery.setParameter(1, nombre);
        return (BigInteger) nativeQuery.getSingleResult();
    }


}
