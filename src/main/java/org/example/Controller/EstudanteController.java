package org.example.Controller;

import org.example.entity.Estudante;
import org.example.Repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository estudanteRepository;

    // INSERT (Criar um novo estudante)
    @PostMapping
    public Estudante criarEstudante(@RequestBody Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    // READ (Buscar todos os estudantes)
    @GetMapping
    public List<Estudante> listarTodos() {
        return estudanteRepository.findAll();
    }

    // READ (Buscar um estudante pela matrícula, que agora é a Chave Primária)
    @GetMapping("/{matEstudante}")
    public ResponseEntity<Estudante> buscarPorMatricula(@PathVariable String matEstudante) {
        // Como matEstudante é o @Id agora, usamos o próprio findById
        Optional<Estudante> estudante = estudanteRepository.findById(matEstudante);

        if (estudante.isPresent()) {
            return ResponseEntity.ok(estudante.get());
        }
        return ResponseEntity.notFound().build();
    }

    // UPDATE (Atualizar os dados de um estudante)
    @PutMapping("/{matEstudante}")
    public ResponseEntity<Estudante> atualizarEstudante(@PathVariable String matEstudante, @RequestBody Estudante dadosAtualizados) {
        Optional<Estudante> estudanteExistente = estudanteRepository.findById(matEstudante);

        if (estudanteExistente.isPresent()) {
            Estudante estudante = estudanteExistente.get();

            // Atualizando os novos atributos mapeados do banco
            estudante.setMc(dadosAtualizados.getMc());
            estudante.setAnoIngresso(dadosAtualizados.getAnoIngresso());
            // A matrícula (matEstudante) não é atualizada pois é a Chave Primária

            return ResponseEntity.ok(estudanteRepository.save(estudante));
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE (Apagar um estudante)
    @DeleteMapping("/{matEstudante}")
    public ResponseEntity<Void> eliminarEstudante(@PathVariable String matEstudante) {
        if (estudanteRepository.existsById(matEstudante)) {
            estudanteRepository.deleteById(matEstudante);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}