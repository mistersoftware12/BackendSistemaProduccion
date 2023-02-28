package com.Biblioteca.Service.Cuidad;

import com.Biblioteca.DTO.Cuidad.CuidadRequest;
import com.Biblioteca.DTO.Cuidad.CuidadResponse;
import com.Biblioteca.DTO.empresa.sucursales.SucursalRequest;
import com.Biblioteca.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Cuidad.Cuidad;
import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Repository.CuidadRepository;
import com.Biblioteca.Repository.Empresa.SucursalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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


    /*
    @Transactional
    public boolean actualizardatosSucursal(SucursalRequest sucursalRequest){
        Optional<Sucursal> sucursal = sucursalRepository.findById(sucursalRequest.getId());

        if(sucursal.isPresent()){
            sucursal.get().setNombre(sucursalRequest.getNombre());
            sucursal.get().setLogo(sucursalRequest.getLogo());
            try{
                sucursalRepository.save(sucursal.get());
                return true;
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizo" + ex);
            }
        } else {
            throw new BadRequestException("No existe un evento con id "+sucursalRequest.getId() );
        }
    }*/

    private boolean getNombre(String nombre) {
        return  cuidadRepository.existsByNombre(nombre);
    }


}
