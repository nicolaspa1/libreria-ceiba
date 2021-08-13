package com.ceiba.biblioteca.ports.primary.services;

import com.ceiba.biblioteca.domain.dto.PrestamoDTO;
import com.ceiba.biblioteca.domain.dto.PrestamoRequest;
import com.ceiba.biblioteca.domain.dto.PrestamoResponse;
import org.springframework.stereotype.Service;

@Service
public interface PrestamoService {
    PrestamoRequest insert(PrestamoDTO prestamoDTO);
    PrestamoResponse findById(int id);
}
