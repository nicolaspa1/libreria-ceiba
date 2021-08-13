package com.ceiba.biblioteca.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,unique = true)
    private Integer id;
    @Column(length = 10)
    private String isbn;
    @Column(length = 10)
    private String identificacionUsuario;
    @Column(length = 1)
    private int tipoUsuario;
    private LocalDate fechaMaximaDevolucion;
}
