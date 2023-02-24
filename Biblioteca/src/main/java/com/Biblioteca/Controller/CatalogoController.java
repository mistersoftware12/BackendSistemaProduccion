package com.Biblioteca.Controller;

import com.Biblioteca.DTO.Catalogo.CatalogoRequest;
import com.Biblioteca.DTO.Catalogo.CatalogoResponse;
import com.Biblioteca.Exceptions.Mensaje;
import com.Biblioteca.Service.Catalogo.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

    @Autowired
    CatalogoService catalogoService;


    @PostMapping("/registrarCatalogo")
    public ResponseEntity<?> registroCatalogo(@RequestBody CatalogoRequest request){
        return new ResponseEntity<>( catalogoService.regitrarCatalogo(request), HttpStatus.OK);
    }

    @GetMapping("/allCatalogo")
    public ResponseEntity<List<CatalogoResponse>> allCatalogo(){
        List<CatalogoResponse> allCatalogos = catalogoService.listAllCatalogo();
        return new ResponseEntity<>(allCatalogos, HttpStatus.OK);
    }

    @PutMapping("/updateCatalogo")
    public ResponseEntity<?> updateCatalogo(@RequestBody CatalogoRequest catalogoRequest){
        catalogoService.actualizardatosCatalogo(catalogoRequest);
        return new ResponseEntity(new Mensaje("Catalogo Actualizado"), HttpStatus.OK);
    }
}
