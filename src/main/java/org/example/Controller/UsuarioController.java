package org.example.Controller;

import org.example.entity.Usuario;
import org.example.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CREATE
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // READ (Todos)
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // READ (Por CPF, que agora é a Chave Primária / ID)
    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> buscarPorCpf(@PathVariable Long cpf) {
        // O findById agora recebe o CPF automaticamente, pois ele é o @Id da classe
        Optional<Usuario> usuario = usuarioRepository.findById(cpf);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    // READ (Por Login - Substituiu a busca por e-mail)
    @GetMapping("/login/{login}")
    public ResponseEntity<Usuario> buscarPorLogin(@PathVariable String login) {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    // UPDATE
    @PutMapping("/{cpf}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long cpf, @RequestBody Usuario dadosAtualizados) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(cpf);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();

            // Atualizando todos os atributos novos mapeados do banco
            usuario.setNome(dadosAtualizados.getNome());
            usuario.setDataNascimento(dadosAtualizados.getDataNascimento());
            usuario.setEmail(dadosAtualizados.getEmail());
            usuario.setTelefone(dadosAtualizados.getTelefone());
            usuario.setLogin(dadosAtualizados.getLogin());
            usuario.setSenha(dadosAtualizados.getSenha());

            return ResponseEntity.ok(usuarioRepository.save(usuario));
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long cpf) {
        if (usuarioRepository.existsById(cpf)) {
            usuarioRepository.deleteById(cpf);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}