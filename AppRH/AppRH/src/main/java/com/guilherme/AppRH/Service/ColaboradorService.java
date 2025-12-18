package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Mappers.ColaboradorMapper;
import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import com.guilherme.AppRH.Repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;
    private final DepartamentoRepository departamentoRepository;
    private final ColaboradorMapper colaboradorMapper;
    private final EmailService emailSender;

    public ColaboradorService(ColaboradorRepository colaboradorRepository, DepartamentoRepository departamentoRepository, ColaboradorMapper colaboradorMapper, EmailService emailSender) {
        this.colaboradorRepository = colaboradorRepository;
        this.departamentoRepository = departamentoRepository;
        this.colaboradorMapper = colaboradorMapper;
        this.emailSender = emailSender;
    }

    public ColaboradorDTO Cadastrar(ColaboradorDTO colaboradorDTO){

        Colaborador NovoColaborador = colaboradorMapper.toEntity(colaboradorDTO);
        Colaborador colaboradorCriado = this.colaboradorRepository.save(NovoColaborador);
        ColaboradorDTO dtoResponse = colaboradorMapper.toDTO(colaboradorCriado);
        emailSender.enviarEmailSimples(colaboradorCriado.getColaboradorEmail(),"Seja Bem-Vindo a empresa", "Seja Bem-vindo a nossa empresa "+ colaboradorCriado.getColaboradorNome());
        return dtoResponse;
    }

    public Optional<ColaboradorDTO> BuscarPorId(UUID id){
        Optional<ColaboradorDTO> col = colaboradorRepository.findById(id).map(colaboradorMapper::toDTO);
        return col;
    }
    public void Atualizar(ColaboradorDTO colaborador, UUID id){

        Colaborador col = colaboradorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Colaborador não encontrado"));
        col.setColaboradorNome(colaborador.getColaboradorNome());
        col.setColaboradorEmail(colaborador.getColaboradorEmail());
        col.setColaboradorTelefone(colaborador.getColaboradorTelefone());

        Departamento DepartamentoById = this.departamentoRepository.findById(colaborador.getDepartamentoId())
                .orElse(null);
        col.setDepartamento(DepartamentoById);

        colaboradorRepository.save(col);
    }

    public void Deletar(UUID Id){
       colaboradorRepository.deleteById(Id);
    }
}
