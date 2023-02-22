package com.Biblioteca.Service.Categoria;

import com.Biblioteca.DTO.Categoria.CategoriaRequest;
import com.Biblioteca.DTO.Categoria.CategoriaResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Categoria.Categoria;
import com.Biblioteca.Repository.Categoria.CategoriaRepository;
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
public class CategoriaService {
    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    CategoriaRepository categoriaRepository;


    public boolean regitrarCategoria (CategoriaRequest categoriaRequest  ){

        Categoria newCategoria = new Categoria();


        newCategoria.setNombre(categoriaRequest.getNombre());
        newCategoria.setInicialCodigo(categoriaRequest.getInicialCodigo());

        try {
            categoriaRepository.save(newCategoria);
            return true;
        }catch (Exception e){
            throw new BadRequestException("No se registró la categoria" +e);
        }

    }

     public List<CategoriaResponse> listAllCategoria() {
        List<Categoria> categoria = categoriaRepository.findAll();

        return categoria.stream().map(categoriaRequest->{

            CategoriaResponse response = new CategoriaResponse();

            response.setId(categoriaRequest.getId());
            response.setNombre(categoriaRequest.getNombre());
            response.setInicialCodigo(categoriaRequest.getInicialCodigo());

            return response;
        }).collect(Collectors.toList());
    }

    @Transactional
    public boolean actualizardatosCategoria( CategoriaRequest categoriaRequest ){
        Optional<Categoria> categoria =  categoriaRepository.findById(categoriaRequest.getId());


        if(categoria.isPresent()){
            categoria.get().setNombre( categoriaRequest.getNombre());
            categoria.get().setInicialCodigo(categoriaRequest.getInicialCodigo());

            try{
                categoriaRepository.save(categoria.get());
                return true;
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizo" + ex);
            }
        } else {
            throw new BadRequestException("No existe una categoria con id "+categoriaRequest.getId() );
        }
    }

}
