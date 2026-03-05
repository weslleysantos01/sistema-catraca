package com.catraca.SistemaCatraca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AcessoRepository acessoRepository;

    // GET /usuarios — lista todos
    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // POST /usuarios — cadastra novo usuário
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario novo = new Usuario(usuario.getNome(), usuario.getCpf());
        repository.save(novo);
        return ResponseEntity.ok(novo);
    }

    // PUT /usuarios/desativar — desativa usuário
    @PutMapping("/desativar")
    public ResponseEntity<String> desativar(@RequestParam String cpf) {
        var usuario = repository.findByCpf(cpf);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario u = usuario.get();
        u.setAtivo(false);
        repository.save(u);
        return ResponseEntity.ok("Usuário desativado!");
    }

    // POST /usuarios/validar — valida acesso na catraca
    @PostMapping("/validar")
    public ResponseEntity<String> validar(@RequestParam String id) {
        return repository.findById(id)
                .map(u -> {
                    boolean autorizado = u.isAtivo();

                    // Salva no histórico!
                    acessoRepository.save(new Acesso(u.getId(), u.getNome(), autorizado));

                    if (autorizado) {
                        return ResponseEntity.ok("🟢 ACESSO LIBERADO! Bem vindo, " + u.getNome() + "!");
                    } else {
                        return ResponseEntity.status(403).body("🔴 ACESSO BLOQUEADO! Usuário inativo.");
                    }
                })
                .orElseGet(() -> {
                    // Salva tentativa inválida no histórico
                    acessoRepository.save(new Acesso(id, "Desconhecido", false));
                    return ResponseEntity.status(404).body("🔴 ACESSO BLOQUEADO! QR Code não reconhecido.");
                });
    }

    // GET /usuarios/historico — lista histórico de acessos
    @GetMapping("/historico")
    public List<Acesso> historico() {
        return acessoRepository.findAllByOrderByDataHoraDesc();
    }
}