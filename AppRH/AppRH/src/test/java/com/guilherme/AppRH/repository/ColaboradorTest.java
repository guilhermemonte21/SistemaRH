package com.guilherme.AppRH.repository;

import com.guilherme.AppRH.Mappers.ColaboradorMapper;
import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import com.guilherme.AppRH.Repository.DepartamentoRepository;
import com.guilherme.AppRH.Service.ColaboradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ColaboradorServiceTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private DepartamentoRepository departamentoRepository;

    @Mock
    private ColaboradorMapper colaboradorMapper;
    @Mock
    private EmailController emailSender;

    private ColaboradorService colaboradorService;

    @BeforeEach
    void setup() {
        colaboradorService = new ColaboradorService(
                colaboradorRepository,
                departamentoRepository,
                colaboradorMapper,
                emailSender
        );
    }


    @Test
    void deveCadastrarColaboradorComSucesso() {
        UUID id = UUID.randomUUID();

        ColaboradorDTO dtoEntrada = new ColaboradorDTO();
        dtoEntrada.setColaboradorNome("Maria");
        dtoEntrada.setColaboradorEmail("maria@email.com");
        dtoEntrada.setColaboradorTelefone(11999999999L);

        Colaborador entidade = new Colaborador();
        entidade.setColaboradorNome("Maria");

        Colaborador salvo = new Colaborador();
        salvo.setColaboradorId(id);
        salvo.setColaboradorNome("Maria");

        ColaboradorDTO dtoSaida = new ColaboradorDTO();
        dtoSaida.setColaboradorNome("Maria");

        // 🔑 ESSENCIAL
        Mockito.when(colaboradorMapper.toEntity(dtoEntrada))
                .thenReturn(entidade);

        Mockito.when(colaboradorRepository.save(entidade))
                .thenReturn(salvo);

        Mockito.when(colaboradorMapper.toDTO(salvo))
                .thenReturn(dtoSaida);

        ColaboradorDTO resultado =
                colaboradorService.Cadastrar(dtoEntrada);

        assertNotNull(resultado);
        assertEquals("Maria", resultado.getColaboradorNome());
    }


    @Test
    void deveBuscarColaboradorPorIdQuandoExistir() {
        UUID id = UUID.randomUUID();

        Colaborador colaborador = new Colaborador();
        colaborador.setColaboradorId(id);
        colaborador.setColaboradorNome("João");

        ColaboradorDTO dto = new ColaboradorDTO();
        dto.setColaboradorNome("João");

        Mockito.when(colaboradorRepository.findById(id))
                .thenReturn(Optional.of(colaborador));


        Mockito.when(colaboradorMapper.toDTO(colaborador))
                .thenReturn(dto);

        Optional<ColaboradorDTO> resultado =
                colaboradorService.BuscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals("João", resultado.get().getColaboradorNome());
    }

    @Test
    void deveRetornarOptionalVazioQuandoColaboradorNaoExistir() {
        UUID id = UUID.randomUUID();

        Mockito.when(colaboradorRepository.findById(id))
                .thenReturn(Optional.empty());

        Optional<ColaboradorDTO> resultado =
                colaboradorService.BuscarPorId(id);

        assertTrue(resultado.isEmpty());

        Mockito.verify(colaboradorRepository).findById(id);
    }

    @Test
    void deveAtualizarColaboradorComDepartamento() {

        UUID id = UUID.randomUUID();

        Colaborador existente = new Colaborador();
        existente.setColaboradorId(id);

        ColaboradorDTO dtoAtualizacao = new ColaboradorDTO();
        dtoAtualizacao.setColaboradorNome("Carlos");
        dtoAtualizacao.setColaboradorEmail("carlos@email.com");
        dtoAtualizacao.setColaboradorTelefone(11999999999L);

        Integer departamentoId = 1;
        dtoAtualizacao.setDepartamentoId(departamentoId);

        Departamento departamento = new Departamento();
        departamento.setDepartamentoId(departamentoId);
        departamento.setDepartamentoNome("TI");

        Mockito.when(colaboradorRepository.findById(id))
                .thenReturn(Optional.of(existente));

        Mockito.when(departamentoRepository.findById(departamentoId))
                .thenReturn(Optional.of(departamento));


        colaboradorService.Atualizar(dtoAtualizacao, id);


        assertEquals("Carlos", existente.getColaboradorNome());
        assertEquals("carlos@email.com", existente.getColaboradorEmail());
        assertEquals(departamento, existente.getDepartamento());

        Mockito.verify(colaboradorRepository).save(existente);
    }

    @Test
    void deveAtualizarColaboradorSemDepartamentoQuandoNaoEncontrado() {

        UUID id = UUID.randomUUID();

        Colaborador existente = new Colaborador();
        existente.setColaboradorId(id);

        ColaboradorDTO dtoAtualizacao = new ColaboradorDTO();
        dtoAtualizacao.setColaboradorNome("Ana");
        dtoAtualizacao.setColaboradorEmail("ana@email.com");
        dtoAtualizacao.setColaboradorTelefone(11777777777L);
        dtoAtualizacao.setDepartamentoId(99);

        Mockito.when(colaboradorRepository.findById(id))
                .thenReturn(Optional.of(existente));

        Mockito.when(departamentoRepository.findById(99))
                .thenReturn(Optional.empty());


        colaboradorService.Atualizar(dtoAtualizacao, id);


        assertNull(existente.getDepartamento());

        Mockito.verify(colaboradorRepository).save(existente);
    }

    @Test
    void deveLancarErroQuandoColaboradorNaoExistirAoAtualizar() {
        UUID id = UUID.randomUUID();

        ColaboradorDTO dto = new ColaboradorDTO();

        Mockito.when(colaboradorRepository.findById(id))
                .thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> colaboradorService.Atualizar(dto, id)
        );

        assertEquals("Colaborador não encontrado", ex.getMessage());

        Mockito.verify(colaboradorRepository).findById(id);
        Mockito.verifyNoInteractions(departamentoRepository);
    }

    @Test
    void deveDeletarColaboradorPorId() {
        UUID id = UUID.randomUUID();

        colaboradorService.Deletar(id);

        Mockito.verify(colaboradorRepository).deleteById(id);
    }
}

