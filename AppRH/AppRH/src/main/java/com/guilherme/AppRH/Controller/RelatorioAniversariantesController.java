package com.guilherme.AppRH.Controller;


import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Service.RelatorioAniversarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aniversariantes")
@Tag(name = "Relatorio Aniversariantes")
public class RelatorioAniversariantesController {
    private final RelatorioAniversarioService service;

    public RelatorioAniversariantesController(RelatorioAniversarioService service) {
        this.service = service;
    }

    @GetMapping("/{mes}")
    public ResponseEntity<List<ColaboradorDTO>> ListaAniversariantes(@PathVariable("mes") int mes){
        List<ColaboradorDTO> aniversariantes = service.ListaAniversariantes(mes);
        return ResponseEntity.ok().body(aniversariantes);
    }
}
