package com.Biblioteca.Service.Empresa;

import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.DTO.empresa.sucursales.SucursalRequest;
import com.Biblioteca.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Models.Empresa.Sucursal;
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
public class SucursalService {

    @Autowired
    SucursalRepository sucursalRepository;


    public boolean regitrarSucursal (SucursalRequest sucursalRequest){

        //Optional<Sucursal> optionalSucursal = sucursalRepository.findBySucursalLikeIgnoreCase(sucursalRequest.getNombre());

        //Optional<Sucursal> optionalBarrio = barrioRepository.findByBarrioLikeIgnoreCase(barrioRequest.getBarrio());
       // if(!optionalSucursal.isPresent()) {
            Sucursal mewSucursal = new Sucursal();
            mewSucursal.setNombre(sucursalRequest.getNombre());
            mewSucursal.setLogo(sucursalRequest.getLogo());

            try {
                sucursalRepository.save(mewSucursal);
                return true;
            }catch (Exception e){
                throw new BadRequestException("No se registró la sucursal" +e);
            }
       // }else{
         //   throw new BadRequestException("Ya existe una sucursal con ese nombre");
        //}
    }


    public List<SucursalResponse> listAllSucursal() {
        List<Sucursal> sucursal = sucursalRepository.findAll();
        return sucursal.stream().map(sucursalRequest->{
            SucursalResponse response = new SucursalResponse();
            response.setId(sucursalRequest.getId());
            response.setNombre(sucursalRequest.getNombre());
            response.setLogo(sucursalRequest.getLogo());


            return response;
        }).collect(Collectors.toList());
    }


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
    }


}
