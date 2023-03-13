package com.Biblioteca.Service.TipoPago;


import com.Biblioteca.DTO.TipoPago.TipoPagoResponse;
import com.Biblioteca.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Models.Pagos.TipoPago;
import com.Biblioteca.Repository.TipoPago.TipoPagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TipoPagoService {

    @Autowired
    TipoPagoRepository tipoPagoRepository;

    public List<TipoPagoResponse> listAllTipoPago() {
        List<TipoPago> tipo = tipoPagoRepository.findAll();
        return tipo.stream().map(tipoRequest->{
            TipoPagoResponse response = new TipoPagoResponse();

            response.setId(tipoRequest.getId());
            response.setNombre(tipoRequest.getNombre());
            return response;
        }).collect(Collectors.toList());
    }


}
