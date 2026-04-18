package br.com.fiap.controller;

import br.com.fiap.model.PontoColeta;
import br.com.fiap.repository.PontoColetaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PontoColetaController {

    private final PontoColetaRepository repo;

    public PontoColetaController(PontoColetaRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/pontos-coleta")
    public List<PontoColeta> listar() {
        return repo.findAll();
    }
}
