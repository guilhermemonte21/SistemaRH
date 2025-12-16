package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Mappers.ColaboradorMapper;
import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RelatorioAniversarioService {
    private final ColaboradorRepository colaboradorRepository;
    private final ColaboradorMapper mapper;

    public RelatorioAniversarioService(ColaboradorRepository colaboradorRepository, ColaboradorMapper mapper) {
        this.colaboradorRepository = colaboradorRepository;
        this.mapper = mapper;
    }

    public List<ColaboradorDTO> ListaAniversariantes(int mes) {
        List<Colaborador> Aniversariantes = colaboradorRepository.buscarPorMesAniversario(mes);

        if (Aniversariantes.isEmpty()){
            throw new IllegalArgumentException("Mês sem aniversários");
        }
        else {
            return Aniversariantes.stream().map(c -> {
                ColaboradorDTO dto = mapper.toDTO(c);
                return dto;
            }).collect(Collectors.toList());
        }
    }
}
