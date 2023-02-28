package com.Biblioteca.DTO.Extra;

import lombok.Data;

import java.io.Serializable;

@Data
public class MaximoDatoResponse implements Serializable {
    private String  maximoDato;

    public MaximoDatoResponse(String maximoDato) {
        this.maximoDato = maximoDato;
    }

    public MaximoDatoResponse() {

    }
}
