package com.guilherme.AppRH.repository;

import com.guilherme.AppRH.Mappers.ColaboradorMapper;
import com.guilherme.AppRH.Mappers.DepartamentoMapper;
import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.DTO.DepartamentoDto;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Repository.DepartamentoRepository;
import com.guilherme.AppRH.Service.DepartamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartamentoServiceTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    // ⚠️ Mapper REAL (não mockado)
    // Boa prática quando o mapper é simples e não depende de nada
    private DepartamentoMapper departamentoMapper = new DepartamentoMapper();

    @Mock
    private ColaboradorMapper colaboradorMapper;

    @InjectMocks
    private DepartamentoService departamentoService;

    @BeforeEach
    void setup() {
        // Força a injeção correta do mapper real
        departamentoService = new DepartamentoService(
                departamentoRepository,
                colaboradorMapper,
                departamentoMapper
        );
    }

    // ======================================================
    // CadastrarDepartamento
    // ======================================================
    @Test
    void deveCadastrarDepartamentoComSucesso() {
        // Arrange
        DepartamentoDto dtoEntrada = new DepartamentoDto();
        dtoEntrada.setNome("TI");

        Departamento salvo = new Departamento();
        salvo.setDepartamentoId(1);
        salvo.setDepartamentoNome("TI");

        Mockito.when(departamentoRepository.save(Mockito.any(Departamento.class)))
                .thenReturn(salvo);

        // Act
        DepartamentoDto resultado =
                departamentoService.CadastrarDepartamento(dtoEntrada);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("TI", resultado.getNome());

        Mockito.verify(departamentoRepository).save(Mockito.any(Departamento.class));
    }

    // ======================================================
    // BuscarDepartamentoPorId
    // ======================================================
    @Test
    void deveBuscarDepartamentoPorIdQuandoExistir() {
        // Arrange
        Departamento departamento = new Departamento();
        departamento.setDepartamentoId(1);
        departamento.setDepartamentoNome("RH");

        Mockito.when(departamentoRepository.findById(1))
                .thenReturn(Optional.of(departamento));

        // Act
        Optional<DepartamentoDto> resultado =
                departamentoService.BuscarDepartamentoPorId(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("RH", resultado.get().getNome());

        Mockito.verify(departamentoRepository).findById(1);
    }

    @Test
    void deveRetornarOptionalVazioQuandoDepartamentoNaoExistir() {
        Mockito.when(departamentoRepository.findById(99))
                .thenReturn(Optional.empty());

        Optional<DepartamentoDto> resultado =
                departamentoService.BuscarDepartamentoPorId(99);

        assertTrue(resultado.isEmpty());

        Mockito.verify(departamentoRepository).findById(99);
    }

    // ======================================================
    // DeletarDepartamento
    // ======================================================
    @Test
    void deveDeletarDepartamentoPorId() {
        departamentoService.DeletarDepartamento(1);

        Mockito.verify(departamentoRepository).deleteById(1);
    }

    // ======================================================
    // ListarColaboradores
    // ======================================================
    @Test
    void deveListarColaboradoresDoDepartamento() {
        // Arrange
        Colaborador colaborador = new Colaborador();
        colaborador.setColaboradorNome("João");

        Departamento departamento = new Departamento();
        departamento.setDepartamentoId(1);
        departamento.setColaboradorList(List.of(colaborador));

        ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
        colaboradorDTO.setColaboradorNome("João");

        Mockito.when(departamentoRepository.findById(1))
                .thenReturn(Optional.of(departamento));

        Mockito.when(colaboradorMapper.toDTO(colaborador))
                .thenReturn(colaboradorDTO);

        // Act
        List<ColaboradorDTO> resultado =
                departamentoService.ListarColaboradores(1);

        // Assert
        assertEquals(1, resultado.size());
        assertEquals("João", resultado.get(0).getColaboradorNome());

        Mockito.verify(departamentoRepository).findById(1);
        Mockito.verify(colaboradorMapper).toDTO(colaborador);
    }

    @Test
    void deveLancarExcecaoQuandoDepartamentoNaoExistirAoListarColaboradores() {
        Mockito.when(departamentoRepository.findById(99))
                .thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> departamentoService.ListarColaboradores(99)
        );

        assertEquals(
                "Departamento não encontrado com o ID: 99",
                exception.getMessage()
        );

        Mockito.verify(departamentoRepository).findById(99);
        Mockito.verifyNoInteractions(colaboradorMapper);
    }
}

