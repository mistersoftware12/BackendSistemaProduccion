package com.Biblioteca.Service.Ubicacion;

import com.Biblioteca.DTO.Ubicacion.PaisRequest;
import com.Biblioteca.DTO.Ubicacion.PaisResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Ubicacion.Pais;
import com.Biblioteca.Repository.Ubicacion.PaisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PaisService {

    @Autowired
    PaisRepository paisRepository;


    public boolean regitrarPais (PaisRequest paisRequest){


        if(!getNombre(paisRequest.getNombre())){
            Pais newPais = new Pais();
            newPais.setNombre(paisRequest.getNombre());


            try {
                paisRepository.save(newPais);

                return true;
            }catch (Exception e){
                throw new BadRequestException("No se registró el pais" +e);
            }
        }else {
            throw new BadRequestException("Ya existe una pasis con ese nombre");
        }


    }


    public List<PaisResponse> listAllPais() {
        
        List<Pais> pais = paisRepository.findAll();
        return pais.stream().map(paisRequest->{
            PaisResponse response = new PaisResponse();
            response.setId(paisRequest.getId());
            response.setNombre(paisRequest.getNombre());
            return response;
        }).collect(Collectors.toList());
    }


    private boolean getNombre(String nombre) {
        return  paisRepository.existsByNombre(nombre);
    }


}
