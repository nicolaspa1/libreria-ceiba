package com.ceiba.biblioteca.ports.secondary;

import com.ceiba.biblioteca.domain.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    @Query("SELECT p from Prestamo p WHERE p.identificacionUsuario =?1")
    Optional<Prestamo> findPrestamo(String identificacionUsuario);
}
