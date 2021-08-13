package com.ceiba.biblioteca.adapters.primary.services;

import com.ceiba.biblioteca.converters.PrestamoConverter;
import com.ceiba.biblioteca.domain.dto.PrestamoDTO;
import com.ceiba.biblioteca.domain.dto.PrestamoRequest;
import com.ceiba.biblioteca.domain.dto.PrestamoResponse;
import com.ceiba.biblioteca.domain.entity.Prestamo;
import com.ceiba.biblioteca.domain.exceptions.InvalidLoanException;
import com.ceiba.biblioteca.ports.primary.services.PrestamoService;
import com.ceiba.biblioteca.ports.secondary.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public  class PrestamoServiceImplement implements PrestamoService {

    private PrestamoRepository prestamoRepository;

    PrestamoServiceImplement(PrestamoRepository prestamoRepository){
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public PrestamoRequest insert(PrestamoDTO prestamoDTO) {
        int tipoUsuario = prestamoDTO.getTipoUsuario();
        boolean tieneLibro = prestamoRepository.findPrestamo(prestamoDTO.getIdentificacionUsuario()).isPresent();

        if (tipoUsuario>3||tipoUsuario<=0) {
            throw new InvalidLoanException("Tipo de usuario no permitido en la biblioteca");
        }

        if (tieneLibro && tipoUsuario == 3) {
            throw new InvalidLoanException("El usuario con identificación " + prestamoDTO.getIdentificacionUsuario() + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
        }

        Prestamo prestamo = PrestamoConverter.convertPrestamoPojo(prestamoDTO);

        LocalDate fechaMax = fechaMax(tipoUsuario,prestamo);
        prestamo.setFechaMaximaDevolucion(fechaMax);

        prestamo = prestamoRepository.save(prestamo);
        String fechaDevolucion = prestamo.getFechaMaximaDevolucion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new PrestamoRequest(prestamo.getId(),prestamo.getIsbn(),prestamo.getIdentificacionUsuario(),prestamo.getTipoUsuario(), fechaDevolucion);
    }

    @Override
    public PrestamoResponse findById(int id) {
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if (prestamo.isPresent()) {
            return PrestamoConverter.convertPrestamo(prestamo.get());
        } else {
            throw new InvalidLoanException("El prestamo: " + id + ", no fue encontrado");
        }
    }

    private LocalDate fechaMax(int tipoUsuario, Prestamo prestamo){
        int arr[] = {10,8,7};
        LocalDate fechaMax = addDaysSkippingWeekends(prestamo.getFechaMaximaDevolucion(), arr[tipoUsuario-1]);
        return fechaMax;
    }

    private LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int diasSumados = 0;
        while (diasSumados < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++diasSumados;
            }
        }
        return result;
    }

}
