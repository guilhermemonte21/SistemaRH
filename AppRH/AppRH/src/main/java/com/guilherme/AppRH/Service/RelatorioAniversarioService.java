package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Mappers.ColaboradorMapper;
import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
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
            log.error("Mês sem aniversários");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Mês sem aniversários");
        }
        else {
            return Aniversariantes.stream().map(c -> {
                ColaboradorDTO colaboradorDTO = mapper.toDTO(c);
                return colaboradorDTO;
            }).collect(Collectors.toList());
        }
    }
}
