package com.ceiba.biblioteca.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PrestamoResponse {
    private int id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private String fechaMaximaDevolucion;
}
