package com.guilherme.AppRH.Model.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @Column(name = "departamentoId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer DepartamentoId;

    @Column(name = "DepartamentoNome")
    private String DepartamentoNome;

    @Column(name = "ListaColaboradores")
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Colaborador> colaboradorList = new ArrayList<>();

    public Integer getDepartamentoId() {
        return DepartamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        DepartamentoId = departamentoId;
    }

    public String getDepartamentoNome() {
        return DepartamentoNome;
    }

    public void setDepartamentoNome(String departamentoNome) {
        DepartamentoNome = departamentoNome;
    }

    public List<Colaborador> getColaboradorList() {
        return colaboradorList;
    }

    public void setColaboradorList(List<Colaborador> colaboradorList) {
        this.colaboradorList = colaboradorList;
    }
}
