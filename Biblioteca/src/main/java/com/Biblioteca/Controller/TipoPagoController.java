package com.Biblioteca.Controller;

import com.Biblioteca.DTO.TipoPago.TipoPagoResponse;
import com.Biblioteca.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Service.TipoPago.TipoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/tipopago")
public class TipoPagoController {


    @Autowired
    TipoPagoService tipoPagoService;

    @GetMapping("/allTipoPago")
    public ResponseEntity<List<TipoPagoResponse>> allTipoPago(){
        List<TipoPagoResponse> alltipo = tipoPagoService.listAllTipoPago();
        return new ResponseEntity<>(alltipo, HttpStatus.OK);
    }
}
