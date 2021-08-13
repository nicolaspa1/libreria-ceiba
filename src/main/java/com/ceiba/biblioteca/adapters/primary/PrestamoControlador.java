package com.ceiba.biblioteca.adapters.primary;


import com.ceiba.biblioteca.domain.dto.PrestamoDTO;
import com.ceiba.biblioteca.domain.dto.PrestamoRequest;
import com.ceiba.biblioteca.domain.dto.PrestamoResponse;
import com.ceiba.biblioteca.ports.primary.services.PrestamoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    private final PrestamoService prestamoService;
    public PrestamoControlador(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public PrestamoRequest insert(@RequestBody PrestamoDTO prestamoDTO){
        return prestamoService.insert(prestamoDTO);
    }

    @GetMapping(value = "/{id}")
    public PrestamoResponse findById(@PathVariable int id) {
        return prestamoService.findById(id);
    }
}

