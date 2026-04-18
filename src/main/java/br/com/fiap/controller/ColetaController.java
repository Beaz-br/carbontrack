package br.com.fiap.controller;

import br.com.fiap.model.Coleta;
import br.com.fiap.repository.ColetaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coletas")
public class ColetaController {

    private final ColetaRepository repository;

    public ColetaController(ColetaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Coleta criar(@RequestBody Coleta coleta) {
        return repository.save(coleta);
    }

    @GetMapping
    public List<Coleta> listar() {
        return repository.findAll();
    }

}
