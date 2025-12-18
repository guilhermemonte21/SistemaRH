package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Mappers.FeriasMapper;
import com.guilherme.AppRH.Model.DTO.FeriasDTO;
import com.guilherme.AppRH.Model.Entity.RegistroFerias;
import com.guilherme.AppRH.Model.Enum.StatusFerias;
import com.guilherme.AppRH.Repository.RegistroFeriasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegistroFeriasService {


    private final RegistroFeriasRepository registroFeriasRepository;
    private final EmailService emailSender;
    private  final FeriasMapper feriasMapper;


    public RegistroFeriasService(RegistroFeriasRepository registroFeriasRepository, EmailService emailSender, FeriasMapper feriasMapper) {
        this.registroFeriasRepository = registroFeriasRepository;
        this.emailSender = emailSender;
        this.feriasMapper = feriasMapper;
    }

    public FeriasDTO CadastrarFerias(FeriasDTO reg){


            RegistroFerias salvo = registroFeriasRepository.save(feriasMapper.toEntity(reg));
            FeriasDTO dto = feriasMapper.toDTO(salvo);
            return dto;



    }

    public Optional<FeriasDTO> BuscarFeriasById (Integer id){
         Optional<FeriasDTO> reg = registroFeriasRepository.findById(id).map(feriasMapper::toDTO);



            return reg;
    }

    public void DeletarRegistroFeriasById(Integer id){

        registroFeriasRepository.deleteById(id);
    }

    public List<FeriasDTO> ListarRegistros(){
        List<RegistroFerias> listaDeRegistrosFerias = registroFeriasRepository.findAll();
        return listaDeRegistrosFerias.stream().map(c ->
        {
            FeriasDTO feriasDTO = feriasMapper.toDTO(c);
            return feriasDTO;
        }).collect(Collectors.toList());

    }

    public void atualizar(Integer id, StatusFerias statusFerias){
        Optional<RegistroFerias> Ferias = registroFeriasRepository.findById(id);
        Ferias.get().setFeriasStatus(statusFerias);

        RegistroFerias feriasAtualizadas = registroFeriasRepository.save(Ferias.get());

        emailSender.enviarEmailSimples(feriasAtualizadas.getColaborador().getColaboradorEmail(), "Atualização sobre Status das suas Ferias", "Olá" + feriasAtualizadas.getColaborador().getColaboradorNome() + "Seu Gestor viu sobre suas ferias e sua solicitaçao foi" + feriasAtualizadas.getFeriasStatus());
    }

}
