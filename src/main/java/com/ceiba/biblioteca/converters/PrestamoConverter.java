package com.ceiba.biblioteca.converters;

import com.ceiba.biblioteca.domain.dto.PrestamoDTO;
import com.ceiba.biblioteca.domain.dto.PrestamoResponse;
import com.ceiba.biblioteca.domain.entity.Prestamo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PrestamoConverter {

    public static Prestamo convertPrestamoPojo(PrestamoDTO prestamoDTO) {
        return new Prestamo(
                null,
                prestamoDTO.getIsbn(),
                prestamoDTO.getIdentificacionUsuario(),
                prestamoDTO.getTipoUsuario(),
                LocalDate.now());
    }

    public static PrestamoResponse convertPrestamo(Prestamo prestamo) {
        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getIsbn(),
                prestamo.getIdentificacionUsuario(),
                prestamo.getTipoUsuario(),
                prestamo.getFechaMaximaDevolucion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
