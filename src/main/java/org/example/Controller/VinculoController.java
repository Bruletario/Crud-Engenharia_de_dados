package org.example.Controller;

import org.example.entity.Vinculo;
import org.example.Repository.VinculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vinculos")
public class VinculoController {

    @Autowired
    private VinculoRepository vinculoRepository;

    // CREATE
    @PostMapping
    public Vinculo criarVinculo(@RequestBody Vinculo vinculo) {
        return vinculoRepository.save(vinculo);
    }

    // READ (Todos)
    @GetMapping
    public List<Vinculo> listarTodos() {
        return vinculoRepository.findAll();
    }

    // READ (Por ID do Vínculo)
    @GetMapping("/{idVinculo}")
    public ResponseEntity<Vinculo> buscarPorId(@PathVariable Long idVinculo) {
        Optional<Vinculo> vinculo = vinculoRepository.findById(idVinculo);
        if (vinculo.isPresent()) {
            return ResponseEntity.ok(vinculo.get());
        }
        return ResponseEntity.notFound().build();
    }

    // READ (Por Status)
    @GetMapping("/status/{status}")
    public List<Vinculo> buscarPorStatus(@PathVariable String status) {
        // O método no repository precisará ser atualizado para findByStatus
        return vinculoRepository.findByStatus(status);
    }

    // READ (Por Matrícula do Estudante)
    @GetMapping("/estudante/{matEstudante}")
    public List<Vinculo> buscarPorEstudante(@PathVariable String matEstudante) {
        // Como o ID do Estudante virou a matrícula (String), o parâmetro muda
        return vinculoRepository.findByEstudanteMatEstudante(matEstudante);
    }

    // UPDATE
    @PutMapping("/{idVinculo}")
    public ResponseEntity<Vinculo> atualizarVinculo(@PathVariable Long idVinculo, @RequestBody Vinculo dadosAtualizados) {
        Optional<Vinculo> vinculoExistente = vinculoRepository.findById(idVinculo);

        if (vinculoExistente.isPresent()) {
            Vinculo vinculo = vinculoExistente.get();

            // Atualizando os atributos mapeados do banco
            vinculo.setDataEntrada(dadosAtualizados.getDataEntrada());
            vinculo.setStatus(dadosAtualizados.getStatus());
            vinculo.setDataSaida(dadosAtualizados.getDataSaida());

            // Atualiza as chaves estrangeiras
            vinculo.setEstudante(dadosAtualizados.getEstudante());
            vinculo.setCurso(dadosAtualizados.getCurso());

            return ResponseEntity.ok(vinculoRepository.save(vinculo));
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{idVinculo}")
    public ResponseEntity<Void> eliminarVinculo(@PathVariable Long idVinculo) {
        if (vinculoRepository.existsById(idVinculo)) {
            vinculoRepository.deleteById(idVinculo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}