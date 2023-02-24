package com.Biblioteca.Controller;

import com.Biblioteca.DTO.Articulo.ArticuloRequest;
import com.Biblioteca.DTO.Articulo.ArticuloResponse;
import com.Biblioteca.DTO.Catalogo.CatalogoRequest;
import com.Biblioteca.DTO.Persona.PersonaUsuarioResponse;
import com.Biblioteca.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Service.Articulo.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @GetMapping("/allArticulo")
    public ResponseEntity<List<ArticuloResponse>> allArticulo(){

        List<ArticuloResponse> allArticulos = articuloService.listAllArticulo();
        return new ResponseEntity<>(allArticulos, HttpStatus.OK);
    }


    @GetMapping("/allBylistaArticulo/{id}")
    public ResponseEntity<ArticuloResponse > listUsuarioById(@PathVariable Long id){
        ArticuloResponse info = articuloService.articulosById(id);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @PostMapping("/registrarArticulo")
    public ResponseEntity<?> registroArticulo(@RequestBody ArticuloRequest request){

        return new ResponseEntity<>(articuloService.regitrarArticulo(request), HttpStatus.OK);
    }
}
