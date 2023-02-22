package com.Biblioteca.Service.Catalogo;

import com.Biblioteca.DTO.Catalogo.CatalogoRequest;
import com.Biblioteca.DTO.Catalogo.CatalogoResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Catalogo.Catalogo;
import com.Biblioteca.Repository.Catalogo.CatalogoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CatalogoService {

    @Autowired
    CatalogoRepository catalogoRepository;


    public boolean regitrarCatalogo (CatalogoRequest catalogoRequest ){

        Catalogo newCatalogo = new Catalogo();

        newCatalogo.setNombre(catalogoRequest.getNombre());
        newCatalogo.setInicialCodigo(catalogoRequest.getInicialCodigo());

            try {
                catalogoRepository.save(newCatalogo);
                return true;
            }catch (Exception e){
                throw new BadRequestException("No se registró la el catalogo" +e);
            }

    }

    public List<CatalogoResponse> listAllCatalogo() {
        List<Catalogo> catalogo = catalogoRepository.findAll();

        return catalogo.stream().map(catalogoRequest->{

            CatalogoResponse response = new CatalogoResponse();

            response.setId(catalogoRequest.getId());
            response.setNombre(catalogoRequest.getNombre());
            response.setInicialCodigo(catalogoRequest.getInicialCodigo());

            return response;
        }).collect(Collectors.toList());
    }

    @Transactional
    public boolean actualizardatosCatalogo(CatalogoRequest catalogoRequest ){
        Optional<Catalogo> catalogo = catalogoRepository.findById(catalogoRequest.getId());


        if(catalogo.isPresent()){
            catalogo.get().setNombre( catalogoRequest.getNombre());
            catalogo.get().setInicialCodigo(catalogoRequest.getInicialCodigo());

            try{
                catalogoRepository.save(catalogo.get());
                return true;
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizo" + ex);
            }
        } else {
            throw new BadRequestException("No existe un catalogo con id "+catalogoRequest.getId() );
        }
    }


}
