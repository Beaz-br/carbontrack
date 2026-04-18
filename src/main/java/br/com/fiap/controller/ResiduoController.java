package br.com.fiap.controller;

import br.com.fiap.model.Residuos;
import br.com.fiap.repository.ResiduoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/residuos")
public class ResiduoController {

    private final ResiduoRepository repository;

    public ResiduoController(ResiduoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Residuos> listar() {
        return repository.findAll();
    }
}
