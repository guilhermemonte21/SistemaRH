package com.guilherme.AppRH.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "colaborador")
@Getter
@Setter
public class Colaborador {
    @Id
    @Column(name = "ColaboradorId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ColaboradorId;

    @Column(name = "ColaboradorNome")
    private String ColaboradorNome;

    @Column(name = "ColaboradorCpf")
    @CPF
    private String ColaboradorCPF;

    @Column(name = "ColaboradorDataNascimento")
    private LocalDate ColaboradorDataNascimento;

    @Column(name = "ColaboradorEmail")
    private String ColaboradorEmail;

    @Column(name = "ColaboradorTelefone")
    private Long ColaboradorTelefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;


    @OneToMany(mappedBy = "Colaborador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroFerias> ferias = new ArrayList<>();


}
