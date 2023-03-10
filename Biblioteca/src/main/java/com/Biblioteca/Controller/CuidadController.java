package com.Biblioteca.Controller;


import com.Biblioteca.DTO.Ubicacion.CuidadRequest;
import com.Biblioteca.DTO.Ubicacion.CuidadResponse;
import com.Biblioteca.Service.Ubicacion.CuidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/cuidad")
public class CuidadController {

    @Autowired
    CuidadService cuidadService;

    @PostMapping("/registrarCuidad")
    public ResponseEntity<?> registroCuidad(@RequestBody CuidadRequest request){
        return new ResponseEntity<>( cuidadService.regitrarCuidad(request), HttpStatus.OK);
    }

    @GetMapping("/allCuidad")
    public ResponseEntity<List<CuidadResponse>> allCuidad(){
        List<CuidadResponse> allCuidad = cuidadService.listAllCuidad();
        return new ResponseEntity<>(allCuidad, HttpStatus.OK);
    }

}
