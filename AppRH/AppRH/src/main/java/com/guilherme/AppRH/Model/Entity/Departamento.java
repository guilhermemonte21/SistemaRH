package com.guilherme.AppRH.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "departamento")
@Getter
@Setter
public class Departamento {
    @Id
    @Column(name = "departamentoId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer DepartamentoId;

    @Column(name = "DepartamentoNome")
    private String DepartamentoNome;

    @Column(name = "ListaColaboradores")
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Colaborador> colaboradorList = new ArrayList<Colaborador>();

}
