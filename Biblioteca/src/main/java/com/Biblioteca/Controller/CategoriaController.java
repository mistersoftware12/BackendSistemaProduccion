package com.Biblioteca.Controller;

import com.Biblioteca.DTO.Articulo.ArticuloProveedorResponse;
import com.Biblioteca.DTO.Articulo.ArticuloResponse;
import com.Biblioteca.DTO.Categoria.CategoriaRequest;
import com.Biblioteca.DTO.Categoria.CategoriaResponse;
import com.Biblioteca.Exceptions.Mensaje;
import com.Biblioteca.Service.Categoria.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;


    @PostMapping("/registrarCategoria")
    public ResponseEntity<?> registroCategoria(@RequestBody CategoriaRequest request){
        return new ResponseEntity<>( categoriaService.regitrarCategoria(request), HttpStatus.OK);
    }

    @GetMapping("/allCategoria")
    public ResponseEntity<List<CategoriaResponse>> allCategoria(){
        List<CategoriaResponse> allCategoria = categoriaService.listAllCategoria();
        return new ResponseEntity<>(allCategoria, HttpStatus.OK);
    }


    @GetMapping("/allCategoriaEstado/{estado}")
    public ResponseEntity<List<CategoriaResponse>> allArticuloProveedor(@PathVariable boolean estado){
        List<CategoriaResponse> allCategorias = categoriaService.listAllCategoriaEstado(estado);
        return new ResponseEntity<>(allCategorias, HttpStatus.OK);
    }

    @PutMapping("/updateCategoria")
    public ResponseEntity<?> updateCategoria(@RequestBody CategoriaRequest categoriaRequest){
        categoriaService.actualizardatosCategoria(categoriaRequest);
        return new ResponseEntity(new Mensaje("Categoria Actualizado"), HttpStatus.OK);
    }
}
