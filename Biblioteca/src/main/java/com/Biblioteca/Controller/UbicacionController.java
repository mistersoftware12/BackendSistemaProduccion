package com.Biblioteca.Controller;



import com.Biblioteca.DTO.Ubicacion.PaisRequest;
import com.Biblioteca.DTO.Ubicacion.PaisResponse;
import com.Biblioteca.Service.Ubicacion.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionController {

    @Autowired
    PaisService paisService;

    @PostMapping("/registrarPais")
    public ResponseEntity<?> registroPais(@RequestBody PaisRequest request){
        return new ResponseEntity<>( paisService.regitrarPais(request), HttpStatus.OK);
    }

    @GetMapping("/allPais")
    public ResponseEntity<List<PaisResponse>> allPais(){
        List<PaisResponse> allCuidad = paisService.listAllPais();
        return new ResponseEntity<>(allCuidad, HttpStatus.OK);
    }

}
