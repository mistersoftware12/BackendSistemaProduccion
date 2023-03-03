package com.Biblioteca.Service.Ubicacion;

import com.Biblioteca.DTO.Ubicacion.CuidadRequest;
import com.Biblioteca.DTO.Ubicacion.CuidadResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Ubicacion.Cuidad;
import com.Biblioteca.Repository.Ubicacion.CuidadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CuidadService {

    @Autowired
    CuidadRepository cuidadRepository;


    public boolean regitrarCuidad (CuidadRequest cuidadRequest){


        if(!getNombre(cuidadRequest.getNombre())){
            Cuidad newCuidad = new Cuidad();
            newCuidad.setNombre(cuidadRequest.getNombre());


            try {
                cuidadRepository.save(newCuidad);

                return true;
            }catch (Exception e){
                throw new BadRequestException("No se registró la cuidad" +e);
            }
        }else {
            throw new BadRequestException("Ya existe una cuidad con ese nombre");
        }


    }


    public List<CuidadResponse> listAllCuidad() {
        
        List<Cuidad> cuidad = cuidadRepository.findAll();
        return cuidad.stream().map(cuidadRequest->{
            CuidadResponse response = new CuidadResponse();
            response.setId(cuidadRequest.getId());
            response.setNombre(cuidadRequest.getNombre());
            return response;
        }).collect(Collectors.toList());
    }


    private boolean getNombre(String nombre) {
        return  cuidadRepository.existsByNombre(nombre);
    }


}
