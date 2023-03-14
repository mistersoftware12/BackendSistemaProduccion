package com.Biblioteca.Service.Banco;

import com.Biblioteca.DTO.Banco.BancoResponse;
import com.Biblioteca.DTO.Banco.CuentaPersonaResponse;
import com.Biblioteca.Models.Banco.Banco;
import com.Biblioteca.Models.Banco.CuentaPersona;
import com.Biblioteca.Models.Banco.TipoCuenta;
import com.Biblioteca.Repository.Banco.BancoRepository;
import com.Biblioteca.Repository.Banco.CuentaPersonaRepository;
import com.Biblioteca.Repository.Banco.TipoCuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BancoService {

    @Autowired
    private TipoCuentaRepository tipoCuentaRepository;

    @Autowired
    BancoRepository bancoRepository;


    @Autowired
    CuentaPersonaRepository cuentaPersonaRepository;

    /*
    @Autowired
    TipoCuenta tipoCuenta;*/

    public List<BancoResponse> listAllBanco() {
        List<Banco> banco = bancoRepository.findAll();
        return banco.stream().map(bancoRequest->{
            BancoResponse response = new BancoResponse();

            response.setId(bancoRequest.getId());
            response.setNombre(bancoRequest.getNombre());
            return response;
        }).collect(Collectors.toList());
    }

    public List<BancoResponse> listAllTipoCuenta() {
        List<TipoCuenta> tipoCuenta = tipoCuentaRepository.findAll();
        return tipoCuenta.stream().map(bancoRequest->{
            BancoResponse response = new BancoResponse();

            response.setId(bancoRequest.getId());
            response.setNombre(bancoRequest.getNombre());
            return response;
        }).collect(Collectors.toList());
    }

    public List<CuentaPersonaResponse> listAllCuentaPersona() {
        List<CuentaPersona> cuentaPersona =  cuentaPersonaRepository.findAll();
        return cuentaPersona.stream().map(cuentapersonaRequest->{
            CuentaPersonaResponse response = new CuentaPersonaResponse();
            response.setId(cuentapersonaRequest.getId());
            response.setPropietario(cuentapersonaRequest.getPropietario());
            response.setNumeroCuenta(cuentapersonaRequest.getNumeroCuenta());
            response.setEstado(cuentapersonaRequest.isEstado());
            response.setIdBanco(cuentapersonaRequest.getBanco().getId());
            response.setIdTipoCuenta(cuentapersonaRequest.getTipoCuenta().getId());
            return response;
        }).collect(Collectors.toList());
    }





}
