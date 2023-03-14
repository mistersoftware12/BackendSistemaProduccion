package com.Biblioteca.Controller;

import com.Biblioteca.DTO.Banco.BancoResponse;
import com.Biblioteca.DTO.Banco.CuentaPersonaResponse;
import com.Biblioteca.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Service.Banco.BancoService;
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
@RequestMapping("/api/banco")
public class BancoController {

    @Autowired
    BancoService bancoService;

    @GetMapping("/allBanco")
    public ResponseEntity<List<BancoResponse>> allBanco(){
        List<BancoResponse> allBanco =  bancoService.listAllBanco();
        return new ResponseEntity<>(allBanco, HttpStatus.OK);
    }

    @GetMapping("/allTipoCuenta")
    public ResponseEntity<List<BancoResponse>> allTipoCuenta(){
        List<BancoResponse> allTipoCuenta =  bancoService.listAllTipoCuenta();
        return new ResponseEntity<>(allTipoCuenta, HttpStatus.OK);
    }

    @GetMapping("/allCuentaPersona")
    public ResponseEntity<List<CuentaPersonaResponse>> allCuentaPersona(){
        List<CuentaPersonaResponse> allCuentaPersona =  bancoService.listAllCuentaPersona();
        return new ResponseEntity<>(allCuentaPersona, HttpStatus.OK);
    }


}
